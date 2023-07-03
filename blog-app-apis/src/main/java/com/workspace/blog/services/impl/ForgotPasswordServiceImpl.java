package com.workspace.blog.services.impl;

import java.util.Properties;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.workspace.blog.service.ForgotPasswordService;
import com.workspace.blog.service.JavaEmailSender;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	
	@Autowired
	private JavaEmailSender emailSender;
	
	@Override
	public String generateOTP() {
		// Generate a random 6-digit OTP
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
	public void sendOTPViaEmail(String email, String otp) {
		this.emailSender.sendEmail(email, "pawarkaran806@gmail.com", "OTP for Password Reset", "Your OTP for password reset is: " + otp);
	}
}
