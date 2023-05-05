package com.example.bank_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_system.service.ifs.BankService;



@CrossOrigin
@RestController
public class BankController {
	
	@Autowired
	private BankService bankService;

}
