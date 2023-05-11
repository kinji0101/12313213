package com.example.bank_system.vo.response;

import java.util.List;

import com.example.bank_system.entity.Bank;
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

	private String account;

	private String password;

	private String email;

	private String phoneNumber;

	private Integer deposit;

	private Integer depositRate;

	private Integer loan;

	private Integer loanRates;

	private Integer offer;

	private String message;

	public BankResponse() {
		super();
	}

	public BankResponse(String card, String name, String address, String account, String password, String email,
			String phoneNumber, Integer deposit, Integer depositRate, Integer loan, Integer loanRates, Integer offer) {
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
		this.loanRates = loanRates;
		this.offer = offer;
	}

	public BankResponse(String message) {
	this.message = message;	
	}
	

	public BankResponse(List<Bank> bankList, String message) {
		super();
		this.bankList = bankList;
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

}
