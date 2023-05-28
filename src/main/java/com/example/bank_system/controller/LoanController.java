package com.example.bank_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_system.service.ifs.LoanService;
import com.example.bank_system.vo.request.BankRequest;
import com.example.bank_system.vo.response.BankResponse;

@CrossOrigin
@RestController
public class LoanController {

	@Autowired
	private LoanService loanService;

	// 貸款
	@PostMapping("/loan")
	public BankResponse loan(@RequestBody BankRequest bankRequest) {
		return loanService.loan(bankRequest.getCard(), bankRequest.getAccount(), bankRequest.getPassword(),
				bankRequest.getLoan(), bankRequest.getInstallments());
	}

	// 查詢貸款款利息金額
	@PostMapping("/get_LoanRate")
	public BankResponse getLoanRate(@RequestBody BankRequest bankRequest) {
		return loanService.getLoanRate(bankRequest.getCard(), bankRequest.getAccount(), bankRequest.getPassword());
	}
	
	//還款
	@PostMapping("/repayment")
	public BankResponse repayment(@RequestBody BankRequest bankRequest) {
		return loanService.repayment(bankRequest.getId(), bankRequest.getCard(), bankRequest.getAccount(), bankRequest.getPassword(), bankRequest.getAmount());
	}

}
