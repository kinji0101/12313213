package com.example.bank_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_system.service.ifs.BankService;
import com.example.bank_system.vo.request.BankRequest;
import com.example.bank_system.vo.response.BankResponse;

@CrossOrigin
@RestController
public class BankController {

	@Autowired
	private BankService bankService;

	// 存款
	@PostMapping("/deposit")
	public BankResponse deposit(@RequestBody BankRequest bankRequest) {
		return bankService.deposit(bankRequest.getCard(), bankRequest.getAccount(), bankRequest.getPassword(),
				bankRequest.getAmount());
	}

}
