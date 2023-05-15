package com.example.bank_system.vo.request;

import java.time.LocalDateTime;
import java.util.List;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.entity.Loan;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankRequest {

	@JsonProperty("bank")
	private Bank bank;

	@JsonProperty("bank_list")
	private List<Bank> bankList;

	@JsonProperty("loans")
	private List<Loan> loans;

	public String card;

	public String name;

	public String address;

	public String account;

	public String password;

	public String email;

	public String phoneNumber;

	public Double deposit;

	public Double depositRate;

	public Double loan;

	public Double loanRate;

	public Integer id;

	public Integer offer;

	public LocalDateTime createDate;

	public Integer amount;

	public Integer installments;

	public BankRequest() {
		super();
	}

	public BankRequest(Bank bank, List<Bank> bankList, List<Loan> loans, String card, String name, String address,
			String account, String password, String email, String phoneNumber, Double deposit, Double depositRate,
			Double loan, Double loanRate, Integer id, Integer offer, LocalDateTime createDate, Integer amount,
			Integer installments) {
		super();
		this.bank = bank;
		this.bankList = bankList;
		this.loans = loans;
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
		this.id = id;
		this.offer = offer;
		this.createDate = createDate;
		this.amount = amount;
		this.installments = installments;
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

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

}
