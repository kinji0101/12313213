package com.example.bank_system.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateRequest {
	
	@JsonProperty("card")
	public String card;

	@JsonProperty("name")
	public String name;

	@JsonProperty("address")
	public String address;

	@JsonProperty("account")
	public String account;

	@JsonProperty("password")
	public String password;

	@JsonProperty("email")
	public String email;

	@JsonProperty("phoneNumber")
	public String phoneNumber;
	
	@JsonProperty("deposit")
	public Integer deposit;

	@JsonProperty("depositRate")
	public Integer depositRate;
	
	@JsonProperty("loan")
	public Integer loan;

	@JsonProperty("loanRate")
	public Integer loanRate;
	
	@JsonProperty("offer")
	public Integer offer;

	@JsonProperty("amount")
	public Integer amount;

	public UpdateRequest() {
		super();
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

	public Integer getLoanRate() {
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
