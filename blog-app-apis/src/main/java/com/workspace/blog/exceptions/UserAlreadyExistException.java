package com.workspace.blog.exceptions;

import com.workspace.blog.payloads.UserDto;

public class UserAlreadyExistException extends RuntimeException{
	String message;
	public UserAlreadyExistException(String message) {
		super();
		this.message = message;
	}
	
}
