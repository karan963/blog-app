package com.workspace.blog.service;


public interface SmsService {
	public void sendSms(String toPhoneNumber, String message);
	public String generateSmsOTP();
}
