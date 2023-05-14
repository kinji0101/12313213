package com.example.bank_system.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
					return new BankResponse(request.getCard(),card.getName(), message);
				}
			}
		}
		return new BankResponse(reqCard, "帳號錯誤或密碼錯誤!");
	}

}
