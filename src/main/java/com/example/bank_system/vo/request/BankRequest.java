package com.example.bank_system.vo.request;

import java.util.List;

import com.example.bank_system.entity.Bank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankRequest {
	
	@JsonProperty("bank")
	private Bank bank;
	
	@JsonProperty("bank_list")
	private List<Bank> bankList;
	

	public String card;

	public String name;

	public String address;

	public String account;

	public String password;

	public String email;

	public String phoneNumber;

	public Integer deposit;

	public Integer depositRate;

	public Integer loan;

	public Integer loanRate;

	public Integer offer;

	public Integer amount;

	public BankRequest() {
		super();
	}

	public BankRequest(String card, String name, String address, String account, String password, String email,
			String phoneNumber, Integer deposit, Integer depositRate, Integer loan, Integer loanRate, Integer offer,
			Integer amount) {
		super();
		this.card = card;
		this.name = name;
		this.address = address;
		this.account = account;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.deposit = deposit;
		this.depositRate = depositRate;
		this.loan = loan;
		this.loanRate = loanRate;
		this.offer = offer;
		this.amount = amount;
	}
	
	

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<Bank> getBankList() {
		return bankList;
	}

	public void setBankList(List<Bank> bankList) {
		this.bankList = bankList;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public Integer getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(Integer depositRate) {
		this.depositRate = depositRate;
	}

	public Integer getLoan() {
		return loan;
	}

	public void setLoan(Integer loan) {
		this.loan = loan;
	}

	public Integer getLoanRates() {
		return loanRate;
	}

	public void setLoanRate(Integer loanRate) {
		this.loanRate = loanRate;
	}

	public Integer getOffer() {
		return offer;
	}

	public void setOffer(Integer offer) {
		this.offer = offer;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
