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
	
	private Integer phoneNumber;
	
	private Integer deposit;
	
	private Integer depositRate;
	
	private Integer  loan;
	
	private Integer loanRates;
	
	private Integer offer;
	
	private String messege;

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

	public String getMessege() {
		return messege;
	}

	public void setMessege(String messege) {
		this.messege = messege;
	}
	
	

	public BankResponse(String card, String name, String address, Integer phoneNumber, Integer deposit,
			Integer depositRate, Integer loan, Integer loanRates, Integer offer, String messege) {
		super();
		this.card = card;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.deposit = deposit;
		this.depositRate = depositRate;
		this.loan = loan;
		this.loanRates = loanRates;
		this.offer = offer;
		this.messege = messege;
	}

	public BankResponse(String messege) {
		super();
		this.messege = messege;
	}

	public BankResponse(String card, String messege) {
		super();
		this.card = card;
		this.messege = messege;
	}

	public BankResponse(Bank bank) {
		super();
		this.bank = bank;
	}
	
	


	
	
	

}
