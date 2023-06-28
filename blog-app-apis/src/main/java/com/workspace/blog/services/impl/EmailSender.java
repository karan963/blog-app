package com.workspace.blog.services.impl;

import java.util.Properties;

import org.springframework.stereotype.Service;

import com.workspace.blog.payloads.ApiResponse;
import com.workspace.blog.service.JavaEmailSender;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailSender implements JavaEmailSender {

	@Override
	public boolean sendEmail(String to,String from,String subject, String text) {
		boolean flag = false;

		// logic
		// smtp properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.auth", true);

		final String username = "pawarkaran806";
		final String password = "kniuukawwpolnrst";
		// session
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress((String)to));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}
