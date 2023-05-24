package com.example.bank_system.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	
	@Override
	public BankResponse login(BankRequest request) {
	    String reqCard = request.getCard();
	    String reqPassword = request.getPassword();
	    if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
	        return new BankResponse("請確實輸入卡號和密碼");
	    }

	    Optional<Bank> optionalCard = bankDao.findById(reqCard);
	    if (!optionalCard.isPresent()) {
	        return new BankResponse("卡號不存在");
	    }

	    List<Bank> pwd = bankDao.findByPassword(reqPassword);
	    if (pwd.isEmpty()) {
	        return new BankResponse("密碼不存在");
	    }

	    for (Bank bank : pwd) {
	        if (bank.getCard().equals(reqCard) && bank.getPassword().equals(reqPassword)) {
	            return new BankResponse(bank.getCard(), bank.getName(), bank.getOffer(), "登入成功");
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
	    Optional<Bank> optionalCard = bankDao.findById(reqCard);
	    if (!optionalCard.isPresent()) {
	        return new BankResponse("卡號不存在");
	    }
	    Bank card = optionalCard.get();
	    List<Bank> pwdList = bankDao.findByPassword(reqPassword);
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
	    Integer reqWithdraw = request.getWithdraw();
	    if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
	        return new BankResponse("請確實輸入卡號和密碼");
	    }
	    Optional<Bank> optionalCard = bankDao.findById(reqCard);
	    if (!optionalCard.isPresent()) {
	        return new BankResponse("卡號不存在");
	    }
	    Bank card = optionalCard.get();
	    List<Bank> pwd = bankDao.findByPassword(reqPassword);
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

	
	@Transactional
	@Override
	public BankResponse transferMoney(BankRequest request) {
	    String reqCard = request.getCard();
	    String reqPassword = request.getPassword();
	    String reqName = request.getName();
	    String reqCard2 = request.getCard2();
	    int reqTransMoney = request.getTransferMoney();
	    if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword) ||!StringUtils.hasText(reqCard2)) {
	        return new BankResponse("請確實輸入卡號和密碼");
	    }

	    Optional<Bank> OPTransferOut = bankDao.findById(reqCard);
	    Optional<Bank> OPTransferOut2 = bankDao.findById(reqCard2);
	    
	    if (OPTransferOut == null || OPTransferOut2 == null||!OPTransferOut2.isPresent()||!OPTransferOut.isPresent()) {
	        return new BankResponse("卡號不存在");
	    }
	    Bank transferOut = OPTransferOut.get();
	    Bank transferIn = bankDao.findByName(reqName);
	    if (transferIn == null) {
	        return new BankResponse("轉入帳戶不存在");
	    }

	    if (transferIn.getCard().equals(transferOut.getCard())) {
	        return new BankResponse("無法轉帳給自己");
	    }
	    
	    if (!transferOut.getCard().equals(reqCard) || !transferOut.getPassword().equals(reqPassword)) {
	        return new BankResponse("卡號和密碼不匹配");
	    }

	    if (!transferIn.getName().equals(reqName) || !transferIn.getCard().equals(reqCard2)) {
	        return new BankResponse("卡號與轉入者不匹配");
	    }

	    if (reqTransMoney <= 0) {
	        return new BankResponse("轉帳失敗");
	    }
	    
	    if (reqTransMoney > transferOut.getDeposit()){
	    	return new BankResponse("轉帳失敗");
	    }
	    int offerCount = request.getOffer(); 

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

	    return new BankResponse(transferOut.getName(), transferOut.getDeposit(),transferOut.getOffer(), "轉帳成功");
	}
	

}
