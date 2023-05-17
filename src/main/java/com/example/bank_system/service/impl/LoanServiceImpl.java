package com.example.bank_system.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.entity.Loan;
import com.example.bank_system.repository.BankDao;
import com.example.bank_system.repository.LoanDao;
import com.example.bank_system.service.ifs.EmailService;
import com.example.bank_system.service.ifs.LoanService;
import com.example.bank_system.vo.response.BankResponse;

@SpringBootApplication
@EnableScheduling
@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanDao loanDao;

	@Autowired
	private BankDao bankDao;

	@Autowired
	private EmailService emailService;

	// 貸款
	@Transactional
	@Override
	public BankResponse loan(String card, String account, String password, Double loan, Integer installments) {
		if (card.isBlank() || account.isBlank() || password.isBlank() || loan == null || installments == null) {
			return new BankResponse("請檢查輸入欄位");
		}

		Optional<Bank> bankOp = bankDao.findById(card);
		if (!bankOp.isPresent()) {
			return new BankResponse("卡號輸入錯誤");
		}

		Bank bank = bankOp.get();
		if (!bank.getAccount().equals(account) || !bank.getPassword().equals(password)) {
			return new BankResponse("帳號或密碼輸入錯誤");
		}

		if (loan <= 0 || loan > 150000) {
			return new BankResponse("貸款金額錯誤");
		}

		if (installments < 3 || installments > 12) {
			return new BankResponse("分期期數錯誤");
		}

		LocalDateTime currentDateTime = LocalDateTime.now();
		Double loanRate = 0.0;

		// get存款判斷信用分數
		Double depoist = bank.getDeposit() + bank.getDepositRate();
		// 將所有貸款相加
		Double totalLoan = loanDao.getTotalLoanAmountByCard(card);

		if (depoist >= 100000) {
			if (totalLoan + loan > 150000) {
				return new BankResponse("信用分數不足");
			}
		}

		if (depoist >= 50000 && depoist < 100000) {
			if (totalLoan + loan > 100000) {
				return new BankResponse("信用分數不足");
			}
		}

		if (depoist < 50000) {
			return new BankResponse("信用分數不足");
		}

		Loan loans = new Loan();
		loans.setLoan(loan);
		loans.setLoanRate(loanRate);
		loans.setInstallments(installments);
		loans.setBank(bank);
		loans.setLoanTime(currentDateTime);

		// 將貸款保存到資料庫
		loanDao.save(loans);
		return new BankResponse("貸款完成");
	}

	// 查詢貸款利息
	@Transactional
	@Override
	public BankResponse getLoanRate(String card, String account, String password) {
		if (card.isBlank() || account.isBlank() || password.isBlank()) {
			return new BankResponse("請檢查輸入欄位");
		}

		Optional<Bank> bankOp = bankDao.findById(card);
		if (!bankOp.isPresent()) {
			return new BankResponse("卡號輸入錯誤");
		}

		Bank bank = bankOp.get();
		if (!bank.getAccount().equals(account) || !bank.getPassword().equals(password)) {
			return new BankResponse("帳號或密碼輸入錯誤");
		}

		List<Loan> loanList = loanDao.findByBank(bank);
		if (loanList.isEmpty()) {
			return new BankResponse("該卡號無貸款資訊");
		}

		List<String> loanInfoList = new ArrayList<>();
		for (Loan loan : loanList) {
			String loanInfo = "貸款金額: " + loan.getLoan() + ", 利息金額: " + loan.getLoanRate();
			loanInfoList.add(loanInfo);
		}
		return new BankResponse(loanInfoList);
	}

	// 結帳帳單
	// @Scheduled(cron = "0 0 0 12 * ?") // 每月1日执行
	@Scheduled(cron = "0 * * * * ?")
	public void statement() {

		System.out.println("定時任務：開始結帳");

		// 獲取當前日期時間
		LocalDateTime currentDateTime = LocalDateTime.now();

		// 獲取所有銀行帳戶
		List<Loan> loans = loanDao.findAll();

		for (Loan bank : loans) {
			Double deposit = bank.getBank().getDeposit();
			Double depositRate = bank.getBank().getDepositRate();
			Double loan = bank.getLoan();
			Double loanRate = bank.getLoanRate();
			LocalDateTime loanTime = bank.getLoanTime();

			if (deposit + depositRate >= 100000) {
				loanRate = loan * 0.05 / 12 + loanRate;
			} else {
				loanRate = loan * 0.08 / 12 + loanRate;
			}
			bank.setLoanRate(loanRate);
			loanDao.save(bank);
		}
		emailService.sendBillingStatements(loans);

	}

	// 還款
	@Transactional
	@Override
	public BankResponse repayment(Integer id, Integer amount) {

		if (id == null || amount == null) {
			return new BankResponse("請檢查輸入欄位");
		}

		Optional<Loan> loanOp = loanDao.findById(id);
		if (!loanOp.isPresent()) {
			return new BankResponse("繳款代碼輸入錯誤");
		}

		Loan loan = loanOp.get();

		if (amount <= 0) {
			return new BankResponse("還款金額錯誤");
		}

		Double repaymentRate = loan.getLoanRate() - amount;
		Double repayment = loan.getLoan();

		// 先扣完利息再扣本金
		if (repaymentRate <= 0) {
			repayment = loan.getLoan() - Math.abs(repaymentRate); // 將剩餘金額作為本金還款
			repaymentRate = 0.0; // 將剩餘利息設置為零
		}

		loan.setLoan(repayment);
		loan.setLoanRate(repaymentRate);
		loanDao.save(loan);

		return new BankResponse("繳款完成");
	}

}
