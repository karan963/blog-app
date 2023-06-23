package com.workspace.blog.exceptions;

public class NonUniqueResultException extends RuntimeException { 
	String message;
	public NonUniqueResultException(String message) {
		super();
		this.message = message;
	}
}
