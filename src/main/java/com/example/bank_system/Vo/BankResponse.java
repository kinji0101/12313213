package com.example.bank_system.Vo;

import java.util.List;

import com.example.bank_system.Entity.Bank;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankResponse {
	
	@JsonProperty("bank")
	private Bank bank;
	
	@JsonProperty("bank_list")
	private List<Bank> bankList;
	
	private String card;
	
	private String name;
	
	private String address;
	
	private String email;
	
	private Integer withdraw;
	
	private Integer phoneNumber;
	
	private Integer deposit;
	
	private Integer depositRate;
	
	private Integer loan;
	
	private Integer loanRates;
	
	private Integer offer;
	
	private Integer transferMoney;
	
	private String message;

	public BankResponse() {
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public BankResponse(String card, String name, String address, Integer phoneNumber, Integer deposit,
			Integer depositRate, Integer loan, Integer loanRates, Integer offer, String message) {
		super();
		this.card = card;
		this.name = name;
	}

	public BankResponse(String message) {
		super();
		this.message = message;
	}

	public BankResponse(String card, String name, String message) {
		super();
		this.card = card;
		this.name = name;
		this.message = message;
	}

	public BankResponse(String card, String message) {
		super();
		this.card = card;
		this.message = message;
	}

	public BankResponse(String card, String name, Integer offer, String message) {
		super();
		this.card = card;
		this.name = name;
		this.offer = offer;
		this.message = message;
	}

	public BankResponse(String name, Integer deposit, Integer offer, String message) {
		super();
		this.name = name;
		this.deposit = deposit;
		this.offer = offer;
		this.message = message;
	}

	
	
	
	
	
}
