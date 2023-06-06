package com.example.bank_system.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_system.entity.Bank;

@Repository
public interface BankDao extends JpaRepository<Bank, String> {
	

	public Optional<Bank> findById(String card);
	
 	public Optional<Bank> findByAccount(String account);

	public List<Bank> findByCard(String card);
	

	public List<Bank> findByPassword(String reqPassword);

	public Bank findByName(String reqName);

	public Bank findByCardAndPassword(String reqCard, String reqPassword);
	


}

