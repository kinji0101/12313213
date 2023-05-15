package com.example.bank_system.Service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.bank_system.Entity.Bank;
import com.example.bank_system.Repository.BankDao;
import com.example.bank_system.Service.Ifs.BankService;
import com.example.bank_system.Vo.BankRequest;
import com.example.bank_system.Vo.BankResponse;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao bankDao;

	@Transactional
	@Override
	public BankResponse login(BankRequest request) {
		String reqCard = request.getCard();
		String reqPassword = request.getPassword();
		if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
			return new BankResponse("請確實輸入卡號和密碼");
		}
		Bank card = bankDao.findByCard(reqCard);
		if (card == null) {
			return new BankResponse("卡號不存在");
		}
		List<Bank> pwd = bankDao.findByPassword(reqPassword);
		if (pwd.isEmpty()) {
			return new BankResponse("密碼不存在");
		}
		for (Bank bank : pwd) {
			if (bank.getCard().equals(reqCard) && bank.getPassword().equals(reqPassword)) {
				return new BankResponse(card.getName(), "登入成功");
			}
		}
		return new BankResponse(reqCard, "帳號錯誤或密碼錯誤!");
	}

	@Transactional
	@Override
	public BankResponse getDepositByCardAndPassword(BankRequest request) {
		String reqCard = request.getCard();
		String reqPassword = request.getPassword();
		if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
			return new BankResponse("請確實輸入卡號和密碼");
		}
		Bank card = bankDao.findByCard(reqCard);
		if (card == null) {
			return new BankResponse("卡號不存在");
		}
		List<Bank> pwdList = bankDao.findByPassword(reqPassword);
		if (pwdList == null || pwdList.isEmpty()) {
			return new BankResponse("密碼不存在");
		}
		boolean isPasswordMatched = false;
		for (Bank pwd : pwdList) {
			if (pwd.getCard().equals(reqCard)) {
				isPasswordMatched = true;
				break;
			}
		}
		if (isPasswordMatched) {
			String message = "帳戶餘額：" + card.getDeposit();
			return new BankResponse(request.getCard(), card.getName(), message);
		} else {
			return new BankResponse(request.getCard(), "帳號錯誤或密碼錯誤!");
		}
	}

	@Transactional
	@Override
	public BankResponse withdrawByCardAndPassword(BankRequest request) {
		String reqCard = request.getCard();
		String reqPassword = request.getPassword();
		int reqWithdraw = request.getWithdraw();
		if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
			return new BankResponse("請確實輸入卡號和密碼");
		}
		Bank card = bankDao.findByCard(reqCard);
		if (card == null) {
			return new BankResponse("卡號不存在");
		}
		List<Bank> pwd = bankDao.findByPassword(reqPassword);
		if (pwd.isEmpty()) {
			return new BankResponse("密碼不存在");
		}
		for (Bank bank : pwd) {
			if (bank.getCard().equals(reqCard) && bank.getPassword().equals(reqPassword)) {
				if (reqWithdraw <= 0) {
					return new BankResponse("提款金額不合法");
				} else if (card.getDeposit() < reqWithdraw) {
					return new BankResponse("提款失敗");
				} else {
					card.setDeposit(card.getDeposit() - reqWithdraw);
					bankDao.save(card);
					String message = "帳戶餘額：" + card.getDeposit();
					return new BankResponse(request.getCard(), card.getName(), message);
				}
			}
		}
		return new BankResponse(reqCard, "帳號錯誤或密碼錯誤!");
	}
	

	public class MyUpdater {

		private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

		@Autowired
		private BankDao bankDao;

		public void startUpdating() {
			Calendar cal = Calendar.getInstance();
			int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

			// 設定為每個月的第一天，凌晨12點
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			// 計算每個月的時間間隔，單位為秒
			long interval = TimeUnit.DAYS.toSeconds(daysInMonth);

			scheduler.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					List<Bank> banks = bankDao.findAll(); // 取得所有銀行帳戶
					for (Bank bank : banks) {
						if (bank.getDeposit() >= 50000 && bank.getDeposit() < 100000) { // 如果帳戶餘額超過50000元且小於100000元
							int offerCount = bank.getOffer(); // 取得優惠次數
							if (offerCount < 10) { // 判斷優惠次數是否已達上限
								bank.setOffer(offerCount + 1); // 增加優惠次數
								bankDao.save(bank); // 更新資料庫
							}
						}
						if (bank.getDeposit() >= 100000) {
							int offerCount = bank.getOffer(); // 取得優惠次數
							if (offerCount < 15) { // 判斷優惠次數是否已達上限
								bank.setOffer(offerCount + 1); // 增加優惠次數
								bankDao.save(bank); // 更新資料庫
							}
						}
					}
				}
			}, cal.getTime().getTime(), interval, TimeUnit.SECONDS);
		}

		public void stopUpdating() {
			scheduler.shutdown();
		}
	}

//	@Transactional
//	@Override
//	public BankResponse transferMoney(BankRequest request) {
//		String reqCard = request.getCard();
//		String reqPassword = request.getPassword();
//		String reqName = request.getName();
//		int reqTransMoney = request.getTransferMoney();
//		if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
//			return new BankResponse("請確實輸入卡號和密碼");
//		}
//		Bank transferOut = bankDao.findByCard(reqCard);
//		if (transferOut == null) {
//			return new BankResponse("卡號不存在");
//		}
//		Bank transferIn = bankDao.findByName(reqName);
//		if (transferIn == null) {
//			return new BankResponse("轉入帳戶不存在");
//		}
//		if (transferIn.getCard().equals(transferOut.getCard())) {
//			return new BankResponse("無法轉帳給自己");
//		}
//		if (reqTransMoney <= 0) {
//			return new BankResponse("轉帳失敗");
//		}
//		if (transferOut.getDeposit() < reqTransMoney) {
//			return new BankResponse("餘額不足");
//		}
//		for(int i = 0; i <= request.getOffer(); i++) {
//			if(i <= request.getOffer()) {
//				transferOut.setDeposit(transferOut.getDeposit() - reqTransMoney );							
//			}else {
//				transferOut.setDeposit(transferOut.getDeposit() -10 - reqTransMoney);		
//			}
//		}
//		transferIn.setDeposit(transferIn.getDeposit() + reqTransMoney);
//		bankDao.save(transferOut);
//		bankDao.save(transferIn);
//		String message = "帳戶餘額：" + transferOut.getDeposit();
//		return new BankResponse(reqCard, transferOut.getName(), message, "轉帳成功");
//	}

	@Transactional
	@Override
	public BankResponse transferMoney(BankRequest request) {
		String reqCard = request.getCard();
		String reqPassword = request.getPassword();
		String reqName = request.getName();
		int reqTransMoney = request.getTransferMoney();
		if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
			return new BankResponse("請確實輸入卡號和密碼");
		}

		Bank transferOut = bankDao.findByCard(reqCard);
		if (transferOut == null) {
			return new BankResponse("卡號不存在");
		}

		Bank transferIn = bankDao.findByName(reqName);
		if (transferIn == null) {
			return new BankResponse("轉入帳戶不存在");
		}

		if (transferIn.getCard().equals(transferOut.getCard())) {
			return new BankResponse("無法轉帳給自己");
		}

		if (reqTransMoney <= 0) {
			return new BankResponse("轉帳失敗");
		}

		if (transferOut.getDeposit() < reqTransMoney) {
			return new BankResponse("餘額不足");
		}

		int offerCount = request.getOffer(); // 取得使用者的優惠次數
		int actualTransMoney = reqTransMoney;

		if (offerCount > 0) { // 若使用者有優惠次數
			transferOut.setOffer(offerCount - 1); // 減少一次優惠次數
			actualTransMoney = reqTransMoney; // 實際轉帳金額不變
		} else { // 若使用者無優惠次數
			actualTransMoney += 10; // 扣除10元轉帳費用
		}
		transferOut.setDeposit(transferOut.getDeposit() - actualTransMoney);
		transferIn.setDeposit(transferIn.getDeposit() + actualTransMoney);

		bankDao.save(transferOut);
		bankDao.save(transferIn);

		String message = "帳戶餘額：" + transferOut.getDeposit();
		return new BankResponse(reqCard, transferOut.getName(), message, "轉帳成功");
	}

}
