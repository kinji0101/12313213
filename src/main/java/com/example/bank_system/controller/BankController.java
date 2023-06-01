package com.example.bank_system.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.entity.TransactionHistory;
import com.example.bank_system.service.ifs.BankService;
import com.example.bank_system.service.ifs.TransactionHistoryService;
import com.example.bank_system.vo.request.BankRequest;
import com.example.bank_system.vo.response.BankResponse;

//@CrossOrigin
@RestController
public class BankController {

	@Autowired
	private BankService bankService;
	
	@Autowired
	private TransactionHistoryService transactionHistoryService;

	 public BankController(BankService bankService) {
	        this.bankService = bankService;
	    }
	 public BankController(TransactionHistoryService transactionHistoryService) {
	        this.transactionHistoryService = transactionHistoryService;
	    }
	 
	public BankController() {
		super();
	}
	
	
	// 查詢用戶資料
	@PostMapping("find_by_card")
	public List<Bank> findByCard(@RequestBody BankRequest bankRequest) {
		return bankService.findByCard(bankRequest.getCard());
	}

	// 修改用戶資料
	@PostMapping("update_client")
	public BankResponse updateClient(@RequestBody BankRequest bankRequest) {
		return bankService.updateClient(bankRequest);
	}

	// 存款
	@PostMapping("/deposit")
	public BankResponse deposit(@RequestBody BankRequest bankRequest) {
		return bankService.deposit(bankRequest.getCard(), bankRequest.getAccount(), bankRequest.getPassword(),
				bankRequest.getAmount());
	}

	// 查詢存款利息金額
	@PostMapping("/get_DepositRate")
	public BankResponse getDepositRate(@RequestBody BankRequest bankRequest) {
		return bankService.getDepositRate(bankRequest.getCard(), bankRequest.getAccount(), bankRequest.getPassword());
	}
	

	@PostMapping("/login")
	private BankResponse login(@RequestBody BankRequest request, HttpSession httpSession) {
		httpSession.setAttribute("card", request.getCard());
		httpSession.setAttribute("password", request.getPassword());
		return bankService.login(request);	
	}
	
	@PostMapping("/login2")
	private BankResponse login2(@RequestBody BankRequest request, HttpSession httpSession) {
		httpSession.setAttribute("account", request.getAccount());
		httpSession.setAttribute("password", request.getPassword());
		return bankService.login2(request);	
	}

	@PostMapping("/get_Deposit_By_Card_And_Password")
	private BankResponse getDepositByCardAndPassword(@RequestBody BankRequest request, HttpSession httpSession) {
		httpSession.setAttribute("card", request.getCard());
		httpSession.setAttribute("password", request.getPassword());
		return bankService.getDepositByCardAndPassword(request);
	}

	@PostMapping("/withdraw_By_Card_And_Password")
	private BankResponse withdrawByCardAndPassword(@RequestBody BankRequest request) {
		return bankService.withdrawByCardAndPassword(request);
	}
	@PostMapping("/transfer_Money")
	private BankResponse transferMoney(@RequestBody BankRequest request) {
		return bankService.transferMoney(request);
	}
	
	@PostMapping("find_by_card2")
	public List<TransactionHistory> findByCard2(@RequestBody BankRequest bankRequest) {
		return transactionHistoryService.findByCard2(bankRequest.getCard());
	}
	

}


