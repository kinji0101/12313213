package com.example.bank_system;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bank_system.entity.TransactionHistory;
import com.example.bank_system.repository.TransactionHistoryDao;
import com.example.bank_system.service.impl.BankServiceImpl;

@SpringBootTest
public class XxTest {

	@Autowired
	BankServiceImpl bankServiceImpl;
	
	@Autowired
	TransactionHistoryDao transactionHistoryDao;
	
//	@Test
//	void contextLoads() {
//		bankServiceImpl.getWithdrawAndTransferOutHistoriesByCard("1111");
//
//	}
	
	@Test
	void contextLoads2() {
		List<TransactionHistory> res = transactionHistoryDao.findAll();
		System.out.println(res.size());

	}

}
