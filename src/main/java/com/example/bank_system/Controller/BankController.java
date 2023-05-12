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
	
	@PostMapping("/get_deposit_By_Card")
	private BankResponse getdepositByCardAndPassword(@RequestBody BankRequest request) {
		return service.getdepositByCardAndPassword(request);
	}

}
