package com.example.bank_system.service.ifs;

import com.example.bank_system.vo.response.BankResponse;

public interface BankService {

	//存款
	public BankResponse deposit(String card, String account, String password, Integer amount);
		
	//查詢利息並計算
	public BankResponse getDepositRate(String card, String account, String password);

	
	}
