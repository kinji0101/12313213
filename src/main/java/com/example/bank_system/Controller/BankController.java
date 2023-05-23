package com.example.bank_system.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

//	@PostMapping("/login")
//	private BankResponse login(@RequestBody BankRequest request, HttpSession httpSession) {
//		return service.login(request);
//	}
//	
	@PostMapping("/login")
	private BankResponse login(@RequestBody BankRequest request, HttpSession httpSession) {
		httpSession.setAttribute("card", request.getCard());
		httpSession.setAttribute("password", request.getPassword());
		return service.login(request);	
		
//		BankResponse response = service.login(request);
//		if (response.getMessege() != null) {
//			model.addAttribute("error", response.getMessege()); // 登入失敗，將錯誤訊息傳遞給視圖
//			return "login";
//		} else {
//			return "redirect:/dashboard"; // 登入成功，重定向到儀表板頁面
//		}
	}

	@PostMapping("/get_Deposit_By_Card_And_Password")
	private BankResponse getDepositByCardAndPassword(@RequestBody BankRequest request, HttpSession httpSession) {
		httpSession.setAttribute("card", request.getCard());
		httpSession.setAttribute("password", request.getPassword());
		return service.getDepositByCardAndPassword(request);
	}

	@PostMapping("/withdraw_By_Card_And_Password")
	private BankResponse withdrawByCardAndPassword(@RequestBody BankRequest request) {
		return service.withdrawByCardAndPassword(request);
	}
	@PostMapping("/transfer_Money")
	private BankResponse transferMoney(@RequestBody BankRequest request) {
		return service.transferMoney(request);
	}

}
