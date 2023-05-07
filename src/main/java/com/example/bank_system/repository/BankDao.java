package com.example.bank_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank_system.entity.Bank;

@Repository
public interface BankDao extends JpaRepository<Bank, String> {

}
