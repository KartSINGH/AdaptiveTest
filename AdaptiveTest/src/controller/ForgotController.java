package controller;

import static dao.ForgotUserDao.save;
import static dao.OfyService.ofy;

import javax.servlet.*;
import javax.servlet.http.*;

import entity.UserDetails;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.*;

@SuppressWarnings("serial")
public class ForgotController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String uID = req.getParameter("email");
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		if (ud == null)
			res.setStatus(404);
		else {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			Date date = new Date();
			// Generating Random Password
			String randomKey = UUID.randomUUID().toString();
			// Save Random Password
			save(uID, randomKey, date);
			// Mail Body
			System.out.println(req.getRequestURL());
			String msgBody = "Please Reset Your Password.\nPlease Visit the link to Change the Password:\nhttp://www.mymock-test.appspot.com/forgetPassword?cp="
					+ randomKey + "\nThe Link Expires in 30 minutes.";
			Message msg = new MimeMessage(session);
			// Forgot Email
			try {
				msg.setFrom(new InternetAddress("danish8802204230@gmail.com", "Mock Test"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(uID, "User"));
				msg.setSubject("Forgot Password");
				msg.setText(msgBody);
				Transport.send(msg);
			} catch (MessagingException e) {
				res.setStatus(403);
			}
		}
	}
}