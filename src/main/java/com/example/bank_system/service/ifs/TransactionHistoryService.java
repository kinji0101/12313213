package com.example.bank_system.service.ifs;

import java.util.List;

import com.example.bank_system.entity.TransactionHistory;

public interface TransactionHistoryService {

	public List<TransactionHistory> findByNumber(String number);
	
}

