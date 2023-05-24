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
import org.springframework.util.StringUtils;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.entity.Loan;
import com.example.bank_system.repository.BankDao;
import com.example.bank_system.service.ifs.BankService;
import com.example.bank_system.vo.request.BankRequest;
import com.example.bank_system.vo.response.BankResponse;

@SpringBootApplication
@EnableScheduling
@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao bankDao;

	// 用戶註冊功能
	@Override
	public BankResponse addClient(BankRequest bankRequest) {
		List<Bank> errorBank = new ArrayList<>();
		List<Bank> bankList = bankRequest.getBankList();
		List<Loan> loans = bankRequest.getLoans();

		for (Bank item : bankList) {
			if (!StringUtils.hasText(item.getCard()) || !StringUtils.hasText(item.getName())
					|| !StringUtils.hasText(item.getAddress()) || !StringUtils.hasText(item.getEmail())
					|| item.getPhoneNumber() == null) {
				return new BankResponse("add Client error");

			}
			if (bankDao.existsById(item.getCard())) {
				errorBank.add(item);
			}
		}
		if (!errorBank.isEmpty()) {
			return new BankResponse("此用戶已存在");
		}
		bankDao.saveAll(bankList);
		return new BankResponse(bankList, "用戶註冊成功");
	}

	// 查詢用戶資料
	@Override
	public List<Bank> findByCard(String card) {

		return bankDao.findByCard(card);
	}

	// 修改用戶資料
	@Override
	public BankResponse updateClient(BankRequest bankRequest) {
		List<Bank> errorBank = new ArrayList<>();
		List<Bank> updatedBankList = new ArrayList<>();
		List<Bank> bankList = bankRequest.getBankList();

		for (Bank item : bankList) {
			if (!StringUtils.hasText(item.getCard())) {
				return new BankResponse("請輸入要更新的用戶卡號");
			}

			Bank existingBank = bankDao.findById(item.getCard()).orElse(null);
			if (existingBank == null) {
				errorBank.add(item);
			} else {
				if (StringUtils.hasText(item.getName())) {
					existingBank.setName(item.getName());
				}
				if (StringUtils.hasText(item.getAddress())) {
					existingBank.setAddress(item.getAddress());
				}
				if (StringUtils.hasText(item.getEmail())) {
					existingBank.setEmail(item.getEmail());
				}
				if (item.getPhoneNumber() != null) {
					existingBank.setPhoneNumber(item.getPhoneNumber());
				}
				if (item.getDeposit() != null) {
					existingBank.setDeposit(item.getDeposit());
				}
				if (item.getDepositRate() != null) {
					existingBank.setDepositRate(item.getDepositRate());
				}

//				if (item.getLoan() != null) {
//					existingBank.setLoan(item.getLoan());
//				}
//				if (item.getLoanRate() != null) {
//					existingBank.setLoanRate(item.getLoanRate());
//				}
				if (item.getOffer() != null) {
					existingBank.setOffer(item.getOffer());
				}
				updatedBankList.add(existingBank);
			}
		}
		if (!errorBank.isEmpty()) {
			return new BankResponse("此用戶不存在");
		}
		bankDao.saveAll(updatedBankList);
		return new BankResponse(updatedBankList, "用戶資料更新成功");
	}

	// 存款
	@Transactional
	@Override
	public BankResponse deposit(String card, String account, String password, Integer amount) {

		if (card.isBlank() || account.isBlank() || password.isBlank() || amount == null) {
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

		if (amount <= 0) {
			return new BankResponse("存款金額錯誤");
		}

		Double deposit = amount + bank.getDeposit();
		bank.setDeposit(deposit);
		bankDao.save(bank);
		return new BankResponse("存款完成");
	}

	// 查詢存款利息金額
	@Transactional
	@Override
	public BankResponse getDepositRate(String card, String account, String password) {

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

		Double depositRate = bank.getDepositRate();
		return new BankResponse("目前利息金額為：" + depositRate);
	}

	// 計算存款利息金額
	// @Scheduled(cron = "0 0 15 * * ?") 每日15點開始結帳
	@Scheduled(cron = "0 * * * * ?")
	public void calculateInterest() {

		System.out.println("定時任務：開始計算利息");

		// 獲取當前日期時間
		LocalDateTime currentDateTime = LocalDateTime.now();

		// 獲取所有銀行帳戶
		List<Bank> banks = bankDao.findAll();

		for (Bank bank : banks) {
			Double deposit = bank.getDeposit();
			Double depositRate = bank.getDepositRate();
			LocalDateTime createDate = bank.getCreateDate(); // 獲取帳戶申辦時間

			if (createDate.plusMonths(6).isAfter(currentDateTime)) { // 判断是否為新申辦帳戶
				if (deposit >= 100000) {
					depositRate = deposit * 0.035 / 365 + depositRate;
				} else {
					depositRate = deposit * 0.025 / 365 + depositRate;
				}
			} else {
				if (deposit >= 100000) {
					depositRate = deposit * 0.03 / 365 + depositRate;
				} else {
					depositRate = deposit * 0.02 / 365 + depositRate;
				}
			}

			bank.setDepositRate(depositRate);
			bankDao.save(bank);
		}

	}

}
