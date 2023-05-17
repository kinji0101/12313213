package com.example.bank_system.service.ifs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.bank_system.entity.Loan;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	//獲取貸款表的用戶郵箱
	public void sendBillingStatements(List<Loan> loans) {
		for (Loan loan : loans) {
			String email = loan.getBank().getEmail();
			String statement = generateBillingStatement(loan);

			sendEmail(email, "Monthly Billing Statement", statement);
		}
	}

	//取得貸款資料
	private String generateBillingStatement(Loan loan) {
		Integer id = loan.getId();
		double loanAmount = loan.getLoan();
		double loanRate = loan.getLoanRate();
		double installmentAmount = installmentAmount(loan);

		//設置郵件內容
		StringBuilder statementBuilder = new StringBuilder();
		statementBuilder.append("Loan Id:").append(id).append("\n");
		statementBuilder.append("Loan Amount: $").append(loanAmount).append("\n");
		statementBuilder.append("LoanRate Amount: $").append(loanRate).append("\n");
		statementBuilder.append("Installment Amount: $").append(installmentAmount).append("\n");

		return statementBuilder.toString();
	}

	//計算本期金額
	private double installmentAmount(Loan loan) {
		double loanAmount = loan.getLoan();
		double loanRate = loan.getLoanRate();
		int installments = loan.getInstallments();

		// 根据贷款金额、贷款利率和分期期数计算利息金额
		double installmentAmount = (loanAmount + loanRate) / installments;

		return installmentAmount;
	}

	//使用JavaMailSender接口發送郵件
	public void sendEmail(String toEmail, String subject, String body) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("sr123692010@gmail.com");
			message.setTo(toEmail);
			message.setText(body);
			message.setSubject(subject);

			mailSender.send(message);

			System.out.println("Email sent successfully to: " + toEmail);
		} catch (MailException e) {
			System.out.println("Failed to send email to: " + toEmail);
			e.printStackTrace();
		}
	}

}
