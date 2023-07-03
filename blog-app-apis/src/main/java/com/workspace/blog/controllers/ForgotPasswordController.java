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
import com.workspace.blog.payloads.UserDto;
import com.workspace.blog.service.JavaEmailSender;
import com.workspace.blog.service.UserService;
import com.workspace.blog.services.impl.ForgotPasswordServiceImpl;

@RestController
@RequestMapping("/api/")
public class ForgotPasswordController {
	
	@Autowired
	private JavaEmailSender emailSender;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ForgotPasswordServiceImpl forgotPasswordServiceImpl;
	
	@Autowired
	private UserService userService;

	private Map<String, String> otpMap = new HashMap<>();

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
		// Check if the email exists in your system and is associated with a registered
		// user
		// Implement your own logic to validate the email address
//		User existingUser = this.findUserByEmail(email);
//		if(existingUser != null) {
//			System.out.println("Hii I am Running");
//			throw new NonUniqueResultException("User Already exists");
//		}
		// Generate OTP
		String otp = this.forgotPasswordServiceImpl.generateOTP();

		// Store OTP in the map or your preferred storage mechanism
		otpMap.put(email, otp);
		System.out.println(otpMap);
//		ForgotPassword fp = new ForgotPassword();
//		fp.setEmail(email);
//		fp.setOtp(otp);
//		this.forgotPasswordRepo.save(fp);

		// Send OTP via email
		this.forgotPasswordServiceImpl.sendOTPViaEmail(email, otp);

		return ResponseEntity.ok("OTP sent successfully");
	}

	@PostMapping("/validate-otp")
	public String validateOTP(@RequestParam("email") String email, @RequestParam("otp") String otp,@RequestParam("password")String password) {
		// Retrieve the stored OTP associated with the email address
//        String storedOTP = fop.getOtp();
        String storedOTP = otpMap.get(email);
		
		
		if (storedOTP != null && storedOTP.equals(otp)) {
			// OTP is valid
			boolean resetSuccessful = userService.resetPassword(email,password);
	        
			// Clear the stored OTP from the map
			otpMap.remove(email);
			
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
