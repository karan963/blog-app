package com.workspace.blog.service;

public interface ForgotPasswordService {
	String generateOTP();
	void sendOTPViaEmail(String email, String otp);
}
