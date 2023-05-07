package com.example.bank_system.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.repository.BankDao;
import com.example.bank_system.service.ifs.BankService;
import com.example.bank_system.vo.response.BankResponse;

@SpringBootApplication
@EnableScheduling
@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao bankDao;

	// 存款
	@Transactional
	@Override
	public BankResponse deposit(String card, String account, String password, Integer amount) {

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

		if (amount <= 0) {
			return new BankResponse("存款金額錯誤");
		}

		Integer deposit = bank.getDeposit() + bank.getDepositRate();
		deposit = amount + deposit;
		bank.setDeposit(deposit);
		bankDao.save(bank);
		return new BankResponse("存款完成");
	}

	// 存款
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

		Integer depositRate = bank.getDepositRate();
		return new BankResponse("目前利息金額為：" + depositRate);
	}

	@Scheduled(cron = "0 0 15 * * ?")
	public void calculateInterest() {

		System.out.println("定時任務：開始計算利息");

		// 獲取當前日期時間
		LocalDateTime currentDateTime = LocalDateTime.now();

		// 獲取所有銀行帳戶
		List<Bank> banks = bankDao.findAll();

		for (Bank bank : banks) {
			

			bankDao.save(bank);

		}

	}
}
