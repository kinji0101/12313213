package com.example.bank_system.service.ifs;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank_system.vo.request.BankRequest;

@Service
public class RegistrationService {

	@Autowired
	private EmailService emailService;

	// 生成并发送验证码
	public String sendVerificationCode(String email) {
		// 生成随机验证码
		String verificationCode = generateVerificationCode();

		// 发送带有验证码的电子邮件
		String subject = "Email Verification";
		String body = "驗證碼: " + verificationCode;
		emailService.sendEmail(email, subject, body);

		// 这里可以添加保存验证码等相关逻辑
		return verificationCode;
	}

	// 生成随机验证码的逻辑
	public String generateVerificationCode() {
		// 生成一个六位数的随机数
		int code = 100000 + new Random().nextInt(900000);
		return String.valueOf(code);
	}

	// 根据电子邮箱获取保存的验证码的逻辑
	private String getSavedVerificationCode(String email) {
		// 从缓存或数据库中根据电子邮箱获取保存的验证码
		// 假设从缓存或数据库中获取到了保存的验证码
		String savedCode = ""; // 假设保存的验证码初始为空字符串
		// 获取保存的验证码的逻辑（从缓存或数据库中查询并返回）
		return savedCode;
	}

	// 验证输入的验证码是否匹配
	public boolean verifyCode(String email, String verificationCode) {
		String savedCode = getSavedVerificationCode(email);
		return savedCode != null && savedCode.equals(verificationCode);
	}
}