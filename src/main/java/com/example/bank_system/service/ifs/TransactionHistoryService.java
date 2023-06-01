package com.example.bank_system.service.ifs;

import java.util.List;

import com.example.bank_system.entity.TransactionHistory;

public interface TransactionHistoryService {
	
	 List<TransactionHistory> findByCard2(String card);
}

