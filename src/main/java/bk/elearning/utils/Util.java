package bk.elearning.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

	public final static String DEFAULT_USER_IMAGE="resources/commons/image/default-user.jpg";
	public static Date getDate() {
		Calendar calendar = Calendar.getInstance();
		// java.sql.Date ourJavaDateObject = new
		// java.sql.Date(calendar.getTime().getTime());
		return calendar.getTime();

	}

	// kiem tra dang nhap
	public static boolean isLogged() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// CustomUserDetails user = (CustomUserDetails)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return null != authentication && !("anonymousUser").equals(authentication.getName());
	}

	public static boolean send(String from, String password, String to, String sub, String msg) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);
			message.setText(msg);
			// send message
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			System.out.println("exception : " + e.toString());
			throw new RuntimeException(e);
		}

	}
}
