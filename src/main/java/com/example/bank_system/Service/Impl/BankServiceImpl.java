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
			return new BankResponse("�нT���J�d���M�K�X");
		}
		Bank card = bankDao.findByCard(reqCard);
		if (card == null) {
			return new BankResponse("�d�����s�b");
		}
		List<Bank> pwd = bankDao.findByPassword(reqPassword);
		if (pwd.isEmpty()) {
			return new BankResponse("�K�X���s�b");
		}
		for (Bank bank : pwd) {
			if (bank.getCard().equals(reqCard) && bank.getPassword().equals(reqPassword)) {
				return new BankResponse(card.getName(), "�n�J���\");
			}
		}
		return new BankResponse(reqCard, "�b�����~�αK�X���~!");
	}

	@Transactional
	@Override
	public BankResponse getDepositByCardAndPassword(BankRequest request) {
		String reqCard = request.getCard();
		String reqPassword = request.getPassword();
		if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
			return new BankResponse("�нT���J�d���M�K�X");
		}
		Bank card = bankDao.findByCard(reqCard);
		if (card == null) {
			return new BankResponse("�d�����s�b");
		}
		List<Bank> pwdList = bankDao.findByPassword(reqPassword);
		if (pwdList == null || pwdList.isEmpty()) {
			return new BankResponse("�K�X���s�b");
		}
		boolean isPasswordMatched = false;
		for (Bank pwd : pwdList) {
			if (pwd.getCard().equals(reqCard)) {
				isPasswordMatched = true;
				break;
			}
		}
		if (isPasswordMatched) {
			String message = "�b��l�B�G" + card.getDeposit();
			return new BankResponse(request.getCard(), card.getName(), message);
		} else {
			return new BankResponse(request.getCard(), "�b�����~�αK�X���~!");
		}
	}

	@Transactional
	@Override
	public BankResponse withdrawByCardAndPassword(BankRequest request) {
		String reqCard = request.getCard();
		String reqPassword = request.getPassword();
		int reqWithdraw = request.getWithdraw();
		if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
			return new BankResponse("�нT���J�d���M�K�X");
		}
		Bank card = bankDao.findByCard(reqCard);
		if (card == null) {
			return new BankResponse("�d�����s�b");
		}
		List<Bank> pwd = bankDao.findByPassword(reqPassword);
		if (pwd.isEmpty()) {
			return new BankResponse("�K�X���s�b");
		}
		for (Bank bank : pwd) {
			if (bank.getCard().equals(reqCard) && bank.getPassword().equals(reqPassword)) {
				if (reqWithdraw <= 0) {
				    return new BankResponse("���ڪ��B���X�k");
				} else if (card.getDeposit() < reqWithdraw) {
				    return new BankResponse("���ڥ���");
				} else {
				    card.setDeposit(card.getDeposit() - reqWithdraw);
				    bankDao.save(card);
					String message = "�b��l�B�G" + card.getDeposit();
					return new BankResponse(request.getCard(),card.getName(), message);
				}
			}
		}
		return new BankResponse(reqCard, "�b�����~�αK�X���~!");
	}

}
