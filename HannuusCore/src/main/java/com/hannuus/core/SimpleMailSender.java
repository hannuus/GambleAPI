package com.hannuus.core;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public class SimpleMailSender {
	public static Logger logger = Logger.getLogger(SimpleMailSender.class);
	
	public static boolean sendMail(String subject, String toAddress, String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setToAddress(toAddress);
		mailInfo.setContent(content);
		mailInfo.setSubject(subject);
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setFromAddress("xxx@qq.com");
		mailInfo.setValidate(true);
		mailInfo.setUserName("xxx@qq.com");
		mailInfo.setPassword("xxx");
		//mailInfo.setMailServerPort("465");
		return sendMail(mailInfo);
	}
	
	public static boolean sendMail(MailSenderInfo mailInfo) {
		Properties props = mailInfo.getProperties();
		
		MyAuthenticator authenticator = null;
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		
		Session sendMailSession = Session.getInstance(props, authenticator);
		Message mailMessage = new MimeMessage(sendMailSession);
		
		Transport transport = null;
		try {
			fillMail(sendMailSession, mailMessage, mailInfo);
			transport = sendMailSession.getTransport("smtp");
			transport.connect(mailInfo.getMailServerHost(), Integer.valueOf(mailInfo.getMailServerPort()),
					mailInfo.getUserName(), mailInfo.getPassword());
		    transport.sendMessage(mailMessage, mailMessage.getAllRecipients()); 
		    transport.close();
		} catch (AuthenticationFailedException e) {
		    logger.error(e, e);
	        return false; 
		} catch (MessagingException e) {
		    logger.error(e, e);
	        return false; 
		} catch (UnsupportedEncodingException e) {
		    logger.error(e, e);
	        return false; 
		} finally {
			try {
				if (null != transport && transport.isConnected())
					transport.close();
				} catch (MessagingException e) {
				    logger.error(e, e);
				}
		}
        return true; 
	}
	
	private static boolean fillMail(Session sendMailSession, Message mailMessage, MailSenderInfo mailInfo) 
			throws AddressException, MessagingException, UnsupportedEncodingException {
		if (null != mailInfo.getFromAddress()) {
			if (StringUtils.isNotBlank(mailInfo.getFromPersonal()))
				mailMessage.setFrom(new InternetAddress(mailInfo.getFromAddress(), mailInfo.getFromPersonal()));
			else 
				mailMessage.setFrom(new InternetAddress(mailInfo.getFromAddress()));
		} else {
			return false;
		}
		
		if (null != mailInfo.getToAddress()) {
			InternetAddress[] address = InternetAddress.parse(mailInfo.getToAddress());
			mailMessage.setRecipients(Message.RecipientType.TO, address);
		} else {
			return false;
		}
		
		if (null != mailInfo.getCcAddress()) {
			InternetAddress[] address = InternetAddress.parse(mailInfo.getCcAddress());
			mailMessage.setRecipients(Message.RecipientType.CC, address);
		}
		
		if (null != mailInfo.getBccAddress()) {
			InternetAddress[] address = InternetAddress.parse(mailInfo.getBccAddress());
			mailMessage.setRecipients(Message.RecipientType.BCC, address);
		}
		
		mailMessage.setSubject(mailInfo.getSubject());
		mailMessage.setSentDate(new Date());
		
		Multipart mPart = new MimeMultipart();
		MimeBodyPart mBodyContent = new MimeBodyPart();
		mBodyContent.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
		mPart.addBodyPart(mBodyContent);
		
		//attach file
		if (!ArrayUtils.isEmpty(mailInfo.getAttachFileNames())) {
			for (String fileName : mailInfo.getAttachFileNames()) {
				MimeBodyPart mBodyPart = new MimeBodyPart();
		    	FileDataSource fds = new FileDataSource(fileName);
		    	mBodyPart.setDataHandler(new DataHandler(fds));
		    	mBodyPart.setFileName(MimeUtility.encodeText(new File(fileName).getName()));
		    	mPart.addBodyPart(mBodyPart);
			}
		}
		
		mailMessage.setContent(mPart);
		mailMessage.setSentDate(new Date());
		
		mailMessage.saveChanges();
		return false;
	}
	
	public static void main(String[] args) {
		SimpleMailSender.sendMail("测试邮件", "xxx@qq.com", "测试");
	}
}
