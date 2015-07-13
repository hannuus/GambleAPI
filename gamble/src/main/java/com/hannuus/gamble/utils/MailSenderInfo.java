package com.hannuus.gamble.utils;

import java.util.Properties;

public class MailSenderInfo {
	private String mailServerHost;
	private String mailServerPort = "25";
	private String fromPersonal;
	private String fromAddress;
	private String toAddress;
	private String toAddress2;
	private String ccAddress;
	private String bccAddress;
	private String userName;
	private String password;
	private boolean validate = false;
	private String subject;
	private String content;
	private String[] attachFileNames;
	
	public Properties getProperties() {
		Properties props = new Properties();
    	props.put("mail.smtp.host", this.mailServerHost);
	    props.put("mail.smtp.port", this.mailServerPort);
	    props.put("mail.smtp.auth", validate ? "true" : "false");
	    props.put("mail.transport.protocol","smtp");
	    
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.SocketFactory"); 
		props.setProperty("mail.smtp.socketFactory.fallback", "true"); 
		props.setProperty("mail.smtp.timeout", "25000"); 
		props.setProperty("mail.smtp.starttls.enable", "false"); 

  	    props.setProperty("mail.smtp.socketFactory.fallback", "false"); 
  	    props.setProperty("mail.smtp.socketFactory.port", this.mailServerPort);
  	    
		return props; 	
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromPersonal() {
		return fromPersonal;
	}

	public void setFromPersonal(String fromPersonal) {
		this.fromPersonal = fromPersonal;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
	
	public String getToAddress2() {
		return toAddress2;
	}

	public void setToAddress2(String toAddress2) {
		this.toAddress2 = toAddress2;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("mailServerHost: " + mailServerHost + "\r\n");
		result.append("mailServerPort: " + mailServerPort + "\r\n");
		result.append("fromAddress: " + fromAddress + "\r\n");
		result.append("toAddress: " + toAddress + "\r\n");
		result.append("ccAddress: " + ccAddress + "\r\n");
		result.append("bccAddress: " + bccAddress + "\r\n");
		result.append("userName: " + userName + "\r\n");
		result.append("password: " + password + "\r\n");
		result.append("validate: " + validate + "\r\n");
		result.append("subject: " + subject + "\r\n");
		result.append("content: " + content + "\r\n");
		result.append("attachFileNames: " + attachFileNames + "\r\n");
		return result.toString();
	}
}
