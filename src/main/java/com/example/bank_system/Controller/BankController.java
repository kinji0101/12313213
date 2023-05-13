package com.example.bank_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_system.Service.Ifs.BankService;
import com.example.bank_system.Vo.BankRequest;
import com.example.bank_system.Vo.BankResponse;


@CrossOrigin
@RestController
public class BankController {
	
	@Autowired
	private BankService service;
	
	@PostMapping("/login")
	private BankResponse login(@RequestBody BankRequest request) {
		return service.login(request);
	}
	
	@PostMapping("/get_Deposit_By_Card_And_Password")
	private BankResponse getDepositByCardAndPassword(@RequestBody BankRequest request) {
		return service.getDepositByCardAndPassword(request);
	}

}
