package com.example.bank_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_system.entity.TransactionHistory;

@Repository
public interface TransactionHistoryDao extends JpaRepository<TransactionHistory, Long> {
	
	 List<TransactionHistory> findByCard(String card);

}
