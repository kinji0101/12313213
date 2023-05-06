package com.example.bank_system.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank")
public class bank {
	
	@Id
	@Column(name = "card")
	private String card;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phoneNumber")
	private Integer phoneNumber;
	
	@Column(name = "deposit")
	private Integer deposit;
	
	@Column(name = "depositRate")
	private Integer depositRate;
	
	@Column(name = "loan")
	private Integer  loan;
	
	@Column(name = "loanRates")
	private Integer loanRates;
	
	@Column(name = "offer")
	private Integer offer;

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
	//123121321
	
}
