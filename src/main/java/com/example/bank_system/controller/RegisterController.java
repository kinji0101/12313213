package com.example.bank_system.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bank_system.entity.Bank;
import com.example.bank_system.repository.BankDao;
import com.example.bank_system.service.ifs.RegistrationService;
import com.example.bank_system.vo.request.BankRequest;

@CrossOrigin(origins = "http://*")
@Controller
public class RegisterController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private BankDao bankDao;

	private Map<String, String> verificationCodeCache = new HashMap<>(); // 緩存对象，用于存储验证码和邮箱地址

	// 注册页面
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("bankRequest", new BankRequest());
		return "register";
	}

	// 发送验证码
	@GetMapping("/send-verification-code")
	@ResponseBody
	public Map<String, Object> sendVerificationCode(@RequestParam("email") String email) {
		String verificationCode = registrationService.sendVerificationCode(email);
		verificationCodeCache.put(email, verificationCode);

		String storedVerificationCode = verificationCodeCache.get(email);
		System.out.println(storedVerificationCode);

		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		return response;
	}

	// 验证
	@PostMapping("/verify")
	@ResponseBody
	public Map<String, Object> verify(@RequestBody Map<String, Object> requestBody) {
		String email = (String) requestBody.get("email");
		String code = (String) requestBody.get("code");

		Map<String, Object> response = new HashMap<>();
		String storedVerificationCode = verificationCodeCache.get(email);
		if (storedVerificationCode != null && storedVerificationCode.equals(code)) {
			// 验证码匹配成功
			response.put("success", true);
		} else {
			// 验证码匹配失败
			response.put("success", false);
		}
		return response;
	}

	@PostMapping("/add_client")
	@ResponseBody
	public Map<String, Object> register(@RequestBody Map<String, Object> requestBody) {
		// 提取表单数据
		String card = (String) requestBody.get("cardNum");
		String account = (String) requestBody.get("account");
		String password = (String) requestBody.get("password");
		String name = (String) requestBody.get("name");
		String address = (String) requestBody.get("address");
		String email = (String) requestBody.get("email");
		String phoneNumber = (String) requestBody.get("phoneNumber");

		// 獲取當前日期時間
		LocalDateTime currentDateTime = LocalDateTime.now();

		// 将数据存入数据库
		Bank bank = new Bank();
		bank.setCard(card);
		bank.setAccount(account);
		bank.setPassword(password);
		bank.setName(name);
		bank.setAddress(address);
		bank.setEmail(email);
		bank.setPhoneNumber(phoneNumber);
		bank.setCreateDate(currentDateTime);

		// 保存到数据库
		bankDao.save(bank);

		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		return response;
	}
}