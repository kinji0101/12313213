package com.example.bank_system.vo.request;

public class BankRequest {

	public String card;
	
	public String name;
	
	public String address;
	
	public String email;
	
	public Integer phoneNumber;
	
	public Integer deposit;
	
	public Integer depositRate;
	
	public Integer  loan;
	
	public Integer loanRates;
	
	public Integer offer;

	public BankRequest() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	
	
}
