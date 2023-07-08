package com.workspace.blog.controllers;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

import com.workspace.blog.entities.User;
import com.workspace.blog.exceptions.NonUniqueResultException;
import com.workspace.blog.payloads.SmsRequest;
import com.workspace.blog.payloads.UserDto;
import com.workspace.blog.service.ForgotPasswordService;
import com.workspace.blog.service.JavaEmailSender;
import com.workspace.blog.service.SmsService;
import com.workspace.blog.service.UserService;
import com.workspace.blog.services.impl.ForgotPasswordServiceImpl;

@RestController
@RequestMapping("/api/")
public class ForgotPasswordController {
	
	@Autowired
	private ForgotPasswordServiceImpl forgotPasswordServiceImpl;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SmsService smsService;

	private Map<String, String> otpMap = new HashMap<>();
	private Map<String, String> SmsOtpMap = new HashMap<>();

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestParam("email") String email,@RequestParam("number") String toPhoneNumber) {
		// Generate OTP
		String otp = this.forgotPasswordServiceImpl.generateOTP();
		String smsotp = this.smsService.generateSmsOTP();

		// Store OTP in the map or your preferred storage mechanism
		otpMap.put(email, otp);
		SmsOtpMap.put((String)toPhoneNumber,smsotp);
		System.out.println(otpMap);
		System.out.println(SmsOtpMap);

		// Send OTP via email
		this.forgotPasswordServiceImpl.sendOTPViaEmail(email, otp);
//		String toPhoneNumber = smsRequest.getToPhoneNumber();
//      String message = smsRequest.getMessage();
		this.smsService.sendSms(toPhoneNumber, "Your Otp for verification is:"+ smsotp + "Thank you");

		return ResponseEntity.ok("OTP sent successfully");
	}

	@PostMapping("/validate-otp")
	public String validateOTP(@RequestParam("email") String email, @RequestParam("otp") String otp,@RequestParam("smsotp") String smsotp,@RequestParam("password")String password,@RequestParam("toPhoneNumber") String toPhoneNumber) {
        String storedOTP = otpMap.get(email);
        String SmsOtp = SmsOtpMap.get(toPhoneNumber);
		
		
		if (storedOTP != null && storedOTP.equals(otp) && SmsOtp != null && SmsOtp.equals(smsotp)) {
			// OTP is valid
			boolean resetSuccessful = userService.resetPassword(email,password);
	        
			// Clear the stored OTP from the map
			otpMap.remove(email);
			SmsOtpMap.remove(toPhoneNumber);
			if (resetSuccessful) {
	            return "OTP validated successfully & Password reset successful";
	        } else {
	            return "Password reset failed ";
	        }
		} else {
			// OTP is invalid
			return "Invalid OTP";
		}
	}
}
