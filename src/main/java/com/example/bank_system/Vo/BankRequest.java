package com.example.bank_system.Vo;

import java.util.List;

import com.example.bank_system.Entity.Bank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankRequest {
	
	@JsonProperty("bank")
	private Bank bank;
	
	@JsonProperty("bank_list")
	private List<Bank> bankList;

	private String card;
	
	private String name;
	
	private String password;
	
	private String address;
	
	private Integer withdraw;
	
	private Integer phoneNumber;
	
	private Integer deposit;
	
	private Integer depositRate;
	
	private Integer  loan;
	
	private Integer loanRates;
	
	private Integer offer;
	
	private Integer transferMoney;
	
	
	public BankRequest() {
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

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
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
		return loanRates;
	}

	public void setLoanRates(Integer loanRates) {
		this.loanRates = loanRates;
	}

	public Integer getOffer() {
		return offer;
	}

	public void setOffer(Integer offer) {
		this.offer = offer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Integer withdraw) {
		this.withdraw = withdraw;
	}

	public Integer getTransferMoney() {
		return transferMoney;
	}

	public void setTransferMoney(Integer transferMoney) {
		this.transferMoney = transferMoney;
	}
	
	


	
	
	

}
