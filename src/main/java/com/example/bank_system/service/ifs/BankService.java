package com.example.bank_system.service.ifs;

import java.util.List;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.vo.request.BankRequest;
import com.example.bank_system.vo.response.BankResponse;

public interface BankService {
	
	//用戶註冊功能
	public BankResponse addClient(BankRequest bankRequest);
	
	//查詢用戶資料
	public List<Bank> findByCard(String card);
	
	//修改用戶資料
	public BankResponse updateClient(BankRequest bankRequest);

	//存款
	public BankResponse deposit(String card, String account, String password, Integer amount);
		
	//查詢利息
	public BankResponse getDepositRate(String card, String account, String password);

	}
