package com.example.bank_system.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_system.Entity.Bank;

@Repository
public interface BankDao extends JpaRepository<Bank, String>{

	Bank findByCard(String reqCard);

	List<Bank> findByPassword(String reqPassword);

	Bank findByName(String reqName);

	Bank findByCardAndPassword(String reqCard, String reqPassword);








	


}
