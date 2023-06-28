package com.workspace.blog.service;

public interface JavaEmailSender {
	public boolean sendEmail(String to,String from, String subject, String text);
}
