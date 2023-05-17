package com.example.bank_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.entity.Loan;

@Repository
public interface LoanDao extends JpaRepository<Loan, String> {

	@Query("SELECT SUM(l.loan) FROM Loan l WHERE l.bank.card = :card")
	Double getTotalLoanAmountByCard(@Param("card") String card);

	public List<Loan> findByBank(Bank bank);

	public Optional<Loan> findById(Integer id);

}
