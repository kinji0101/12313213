package com.example.bank_system.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "bank_system")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bank {

	// 卡號
	@Id
	@Column(name = "card")
	private String card;

	// 姓名
	@Column(name = "name")
	private String name;

	// 戶籍地址
	@Column(name = "address")
	private String address;

	// 帳號
	@Column(name = "account")
	private String account;

	// 密碼
	@Column(name = "password")
	private String password;

	// 信箱
	@Column(name = "email")
	private String email;

	// 手機號碼
	@Column(name = "phonenumber")
	private String phoneNumber;

	// 存款金額
	@Column(name = "deposit")
	private Double deposit;

	// 存款利息
	@Column(name = "depositrate")
	private Double depositRate;

	// 貸款額度
	@Column(name = "loan")
	private Double loan;

	// 貸款利息
	@Column(name = "loanrate")
	private Double loanRate;

	// 轉帳優惠
	@Column(name = "offer")
	private Integer offer;
	
	//申辦時間
	@Column(name = "createdate")
	private LocalDateTime  createDate;

	public Bank() {
		super();
	}

	public Bank(String card, String name, String address, String account, String password, String email,
			String phoneNumber, Double deposit, Double depositRate, Double loan, Double loanRate, Integer offer, LocalDateTime  createDate) {
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
		this.createDate = createDate;
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

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(Double depositRate) {
		this.depositRate = depositRate;
	}

	public Double getLoan() {
		return loan;
	}

	public void setLoan(Double loan) {
		this.loan = loan;
	}

	public Double getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(Double loanRate) {
		this.loanRate = loanRate;
	}

	public Integer getOffer() {
		return offer;
	}

	public void setOffer(Integer offer) {
		this.offer = offer;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

}
