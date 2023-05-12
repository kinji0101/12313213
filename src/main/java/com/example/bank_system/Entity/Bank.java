package com.example.bank_system.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank")
public class Bank {
	
	@Id
	@Column(name = "card")
	private String card; //卡號
	
	@Column(name = "password")
	private String password; //密碼
	
	@Column(name = "name")
	private String name; //姓名
	
	@Column(name = "address")
	private String address; //戶籍
	
	@Column(name = "phone_number")
	private Integer phoneNumber; //電話號碼
	
	@Column(name = "deposit")
	private Integer deposit; // 存款金額
	
	@Column(name = "deposit_rate")
	private Integer depositRate; //存款利率
	
	@Column(name = "loan")
	private Integer  loan; //貸款額度
	
	@Column(name = "loan_rate")
	private Integer loanRates; //貸款利率
	
	@Column(name = "offer")
	private Integer offer; //轉帳優惠

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
	
	


	
	
	
	
}
