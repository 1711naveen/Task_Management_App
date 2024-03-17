package com.sam.demo.utility;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMail(String subject, String body, String to, File file) {

		try {
				MimeMessage mimeMessage = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
				helper.setSubject(subject);
				helper.setText(body,true);
				helper.setTo(to);
				helper.addAttachment("Tasks", file);
				
				javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return true;
	}
}
