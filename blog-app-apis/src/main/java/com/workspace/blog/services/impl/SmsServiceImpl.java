package com.workspace.blog.services.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.workspace.blog.service.SmsService;

@Service
public class SmsServiceImpl implements SmsService {
	
	private String accountSid;
    private String authToken;
    private PhoneNumber fromPhoneNumber;
    
    @Autowired
    public SmsServiceImpl(@Value("${twilio.accountSid}") String accountSid,@Value("${twilio.authToken}") String authToken,@Value("${twilio.fromPhoneNumber}") String fromPhoneNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromPhoneNumber = new PhoneNumber(fromPhoneNumber);
    }
    public void sendSms(String toPhoneNumber, String message) {
        Message.creator(new PhoneNumber(toPhoneNumber),fromPhoneNumber, message).create();
    }
    @Override
	public String generateSmsOTP() {
		// Generate a random 6-digit OTP
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

}
