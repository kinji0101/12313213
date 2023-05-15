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
					return new BankResponse(request.getCard(), card.getName(), message);
				}
			}
		}
		return new BankResponse(reqCard, "�b�����~�αK�X���~!");
	}
	

	public class MyUpdater {

		private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

		@Autowired
		private BankDao bankDao;

		public void startUpdating() {
			Calendar cal = Calendar.getInstance();
			int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

			// �]�w���C�Ӥ몺�Ĥ@�ѡA���12�I
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			// �p��C�Ӥ몺�ɶ����j�A��쬰��
			long interval = TimeUnit.DAYS.toSeconds(daysInMonth);

			scheduler.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					List<Bank> banks = bankDao.findAll(); // ���o�Ҧ��Ȧ�b��
					for (Bank bank : banks) {
						if (bank.getDeposit() >= 50000 && bank.getDeposit() < 100000) { // �p�G�b��l�B�W�L50000���B�p��100000��
							int offerCount = bank.getOffer(); // ���o�u�f����
							if (offerCount < 10) { // �P�_�u�f���ƬO�_�w�F�W��
								bank.setOffer(offerCount + 1); // �W�[�u�f����
								bankDao.save(bank); // ��s��Ʈw
							}
						}
						if (bank.getDeposit() >= 100000) {
							int offerCount = bank.getOffer(); // ���o�u�f����
							if (offerCount < 15) { // �P�_�u�f���ƬO�_�w�F�W��
								bank.setOffer(offerCount + 1); // �W�[�u�f����
								bankDao.save(bank); // ��s��Ʈw
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
//			return new BankResponse("�нT���J�d���M�K�X");
//		}
//		Bank transferOut = bankDao.findByCard(reqCard);
//		if (transferOut == null) {
//			return new BankResponse("�d�����s�b");
//		}
//		Bank transferIn = bankDao.findByName(reqName);
//		if (transferIn == null) {
//			return new BankResponse("��J�b�ᤣ�s�b");
//		}
//		if (transferIn.getCard().equals(transferOut.getCard())) {
//			return new BankResponse("�L�k��b���ۤv");
//		}
//		if (reqTransMoney <= 0) {
//			return new BankResponse("��b����");
//		}
//		if (transferOut.getDeposit() < reqTransMoney) {
//			return new BankResponse("�l�B����");
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
//		String message = "�b��l�B�G" + transferOut.getDeposit();
//		return new BankResponse(reqCard, transferOut.getName(), message, "��b���\");
//	}

	@Transactional
	@Override
	public BankResponse transferMoney(BankRequest request) {
		String reqCard = request.getCard();
		String reqPassword = request.getPassword();
		String reqName = request.getName();
		int reqTransMoney = request.getTransferMoney();
		if (!StringUtils.hasText(reqCard) || !StringUtils.hasText(reqPassword)) {
			return new BankResponse("�нT���J�d���M�K�X");
		}

		Bank transferOut = bankDao.findByCard(reqCard);
		if (transferOut == null) {
			return new BankResponse("�d�����s�b");
		}

		Bank transferIn = bankDao.findByName(reqName);
		if (transferIn == null) {
			return new BankResponse("��J�b�ᤣ�s�b");
		}

		if (transferIn.getCard().equals(transferOut.getCard())) {
			return new BankResponse("�L�k��b���ۤv");
		}

		if (reqTransMoney <= 0) {
			return new BankResponse("��b����");
		}

		if (transferOut.getDeposit() < reqTransMoney) {
			return new BankResponse("�l�B����");
		}

		int offerCount = request.getOffer(); // ���o�ϥΪ̪��u�f����
		int actualTransMoney = reqTransMoney;

		if (offerCount > 0) { // �Y�ϥΪ̦��u�f����
			transferOut.setOffer(offerCount - 1); // ��֤@���u�f����
			actualTransMoney = reqTransMoney; // �����b���B����
		} else { // �Y�ϥΪ̵L�u�f����
			actualTransMoney += 10; // ����10����b�O��
		}
		transferOut.setDeposit(transferOut.getDeposit() - actualTransMoney);
		transferIn.setDeposit(transferIn.getDeposit() + actualTransMoney);

		bankDao.save(transferOut);
		bankDao.save(transferIn);

		String message = "�b��l�B�G" + transferOut.getDeposit();
		return new BankResponse(reqCard, transferOut.getName(), message, "��b���\");
	}

}
