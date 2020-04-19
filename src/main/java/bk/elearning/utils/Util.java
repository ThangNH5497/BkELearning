package bk.elearning.utils;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

	public static Date getDate() {
		Calendar calendar = Calendar.getInstance();
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
	
	public static String SOURCE_FOLDER = System.getProperty("user.dir") + "/sources/";
    public static String TARGET_FOLDER = System.getProperty("user.dir") + "/target/";

    public static String getSource(String name){
        return SOURCE_FOLDER + name;
    }

    public static String getOutput(String name){
        return TARGET_FOLDER + name;
    }

    public static void write2File(Mat source, String name){
    	Imgcodecs.imwrite(getOutput(name), source);
    }

    public static void sout(String str){
        System.out.println(str);
    }

    public static void sortTopLeft2BottomRight(List<MatOfPoint> points){
        // top-left to right-bottom sort
        Collections.sort(points, (e1, e2) -> {

            Point o1 = new Point(e1.get(0, 0));
            Point o2 = new Point(e2.get(0, 0));

            return o1.y > o2.y ? 1 : -1;
        });
    }

    public static void sortLeft2Right(List<MatOfPoint> points){
        // left to right sort
        Collections.sort(points, (e1, e2) -> {

            Point o1 = new Point(e1.get(0, 0));
            Point o2 = new Point(e2.get(0, 0));

            return o1.x > o2.x ? 1 : -1;
        });
    }
}
