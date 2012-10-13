package com.insacosa.vo;

import com.insacosa.interfaces.Usuari_Impl;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.*;

import com.insacosa.util.MessageFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.*;

import javax.faces.*;
import javax.faces.validator.*;
import javax.faces.application.*;
import javax.faces.component.*;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import com.sun.mail.smtp.SMTPTransport;

public class RetrievePasswordForm  {
	private String email;
	private boolean isRendered;
	
	private Session session;
	private Transport transport;
	private Message message;

	public String getEmail() {
		return email;
	}	
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIsRendered() {
		return isRendered;
	}	
	public void setIsRendered(boolean isRendered) {
		this.isRendered = isRendered;
	}

	
	public String check() throws Exception{
		String returnString = "";
		if (validateData()) {
			
			
			//Interfaces interfaceS = serviceFinder.findBean("Interfaces");
			Usuari_Impl r = new Usuari_Impl();
			
			Boolean existeixEmail = r.emailValid(this.email);
			if (existeixEmail)
				{
					sendMail(r.passwordEmail(this.email),getEmail());
					returnString = "passwordsendingsuccess";
				}
				else{
					setIsRendered(true);
					returnString = "passwordsendingfail";
				}
		}
		else{
			setIsRendered(false);
			returnString = "passwordsendingfail";
		}
		return returnString;
	}

	public boolean validateData() {
		boolean status = true;
		MessageFactory mf = new MessageFactory();
		FacesContext ctx = FacesContext.getCurrentInstance();

		Pattern p2 = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m2 = p2.matcher(email);
		boolean matchFound2 = m2.matches();

		if (email.length()==0) {
			ctx.addMessage("sendPasswordByMail:email",new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorEmailMsg"), null));
			status = false;
		}
		if (!matchFound2) {
			ctx.addMessage("sendPasswordByMail:email",new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorEmail"), null));
			status = false;
		}
		
		return status;
	}

	public void setMessage(String fromAddress,String toAddress,String subject,String content) throws Exception {
		String[] addresses = {toAddress};
		setMessage(fromAddress,addresses,subject,content);
	}

	public void sendMail(String sendPassword, String email) throws Exception {
	
		String from = "admin@roseindia.net ";
		String to[] = new String[]{email};
		String subject = "Account Password";

		String content = "Your password is : \""+ sendPassword+"\"";

		setMessage(from,to,subject,content);
		//setSMTPServer("smtp.mail.yahoo.es",25,"santasusanap@yahoo.es","tivoli00");
		setSMTPServer("smtp.gmail.com",587,"santasusanap@gmail.com","tivoli00");

		send();
	}

	public void setMessage(String fromAddress,String[] toAddresses,String subject,String content) throws Exception {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		//session = Session.getInstance(System.getProperties());		
		session = Session.getInstance(props);
	
		message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(fromAddress));
		InternetAddress[] toIntAdds = new InternetAddress[toAddresses.length];

		for (int i=0;i<toAddresses.length;i++)
		toIntAdds[i] = new InternetAddress(toAddresses[i]);

		message.setRecipients(Message.RecipientType.TO,toIntAdds);
		message.setSubject(subject);
		message.setSentDate(new java.util.Date());
		message.setText(content);
	}

	public void setSMTPServer(String host,int port,String user,String password) throws Exception{
		//transport = new SMTPTransport(session,new URLName(host));
		transport = session.getTransport("smtp");
		
		transport.connect(host,port,user,password);
	}

	public void send() throws Exception{
		message.saveChanges();
		transport.sendMessage(message, message.getAllRecipients());
		System.out.println("Message is sent.");
		transport.close();
	}
}