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
public class BankServiceImpl implements BankService{
	
	@Autowired
	private BankDao bankDao;
	
	@Transactional
	@Override
	public BankResponse getdepositByCardAndPassword(BankRequest request) {
		String reqCard = request.getCard();;
		String reqPassword = request.getPassword();;
		if (!StringUtils.hasText(reqCard)||!StringUtils.hasText(reqPassword)) {
			return new BankResponse("�нT���J�d���M�K�X");
		}
		Bank card = bankDao.findByCard(reqCard);
		if ( card == null) {
			return new BankResponse("�d�����s�b");
		}
		Bank pwd = bankDao.findByPassword(reqPassword);
		if ( pwd == null) {
			return new BankResponse("�K�X���s�b");
		}
		return new BankResponse( "�n�J���\");
	}




}
