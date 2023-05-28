package com.example.bank_system.service.ifs;

import com.example.bank_system.vo.response.BankResponse;

public interface LoanService {

	// 貸款
	public BankResponse loan(String card, String account, String password, Double loan, Integer installments);

	// 查詢貸款利息
	public BankResponse getLoanRate(String card, String account, String password);

	// 還款
	public BankResponse repayment(Integer id, String card, String account, String password, Integer amount);

}
