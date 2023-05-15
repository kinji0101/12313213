package com.example.bank_system.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "loan")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "card", referencedColumnName = "card")
	private Bank bank;

	// 貸款額度
	@Column(name = "loan")
	private Double loan;

	// 貸款利息
	@Column(name = "loanrate")
	private Double loanRate;

	// 分期期數
	@Column(name = "installments")
	private Integer installments;

	// 貸款時間
	@Column(name = "loantime")
	private LocalDateTime loanTime;

	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Loan(Integer id, Bank bank, Double loan, Double loanRate, Integer installments, LocalDateTime loanTime) {
		super();
		this.id = id;
		this.bank = bank;
		this.loan = loan;
		this.loanRate = loanRate;
		this.installments = installments;
		this.loanTime = loanTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
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

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public LocalDateTime getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(LocalDateTime loanTime) {
		this.loanTime = loanTime;
	}
	
	

}