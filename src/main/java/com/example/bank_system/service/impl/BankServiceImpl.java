package com.example.bank_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank_system.repository.BankDao;
import com.example.bank_system.service.ifs.BankService;

@Service
public class BankServiceImpl implements BankService{

	@Autowired
	private BankDao bankDao;
}
