package com.example.bank_system.vo.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankResponse {

	private String card;

	private String name;

	private String address;

	private String account;

	private String password;

	private String email;

	private String phoneNumber;

	private Double deposit;

	private Double depositRate;

	private Double loan;

	private Double loanRate;

	private Integer offer;

	private LocalDateTime createDate;

	private String message;

	public BankResponse() {
		super();
	}

	public BankResponse(String card, String name, String address, String account, String password, String email,
			String phoneNumber, Double deposit, Double depositRate, Double loan, Double loanRate, Integer offer,
			LocalDateTime createDate, String message) {
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
		this.message = message;
	}

	public BankResponse(String message) {
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
