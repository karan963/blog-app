package com.workspace.blog.controllers;

import java.util.Properties;


import org.springframework.beans.factory.annotation.Autowired;
import com.workspace.blog.exceptions.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workspace.blog.payloads.JwtAuthRequest;
import com.workspace.blog.payloads.JwtAuthResponse;
import com.workspace.blog.payloads.UserDto;
import com.workspace.blog.security.JwtTokenHelper;
import com.workspace.blog.service.UserService;
import com.workspace.blog.services.impl.EmailSender;

import jakarta.mail.MessagingException;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailSender emailSender;
	
	
	
	@PostMapping(value="/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username,password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}
		catch(BadCredentialsException e) {
			System.out.println("Invalid Details");
			throw new ApiException("Invalid username or password");
		}
	}
	
	//register new user api
	@PostMapping(value="/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) throws MessagingException{
//		UserDto registeredUser = this.userService.registerNewUser(userDto);
		UserDto registeredUser=null;
		boolean b = this.emailSender.sendEmail(userDto.getEmail(),"pawarkaran806@gmail.com", "Ae Pagal Email Bagh", "Hello, "+userDto.getName()+" Your Phone has been Hacked and also account, didi tu pagal ahes tu yedi ahes tu moti ahes ani kaali pan ahees");
		if(b) {
			registeredUser = this.userService.registerNewUser(userDto);
		}
		else {
			throw new ApiException("User can't be registered as email can't be send");
		}
		return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}
}
