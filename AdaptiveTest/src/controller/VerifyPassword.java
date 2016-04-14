package controller;

import static dao.OfyService.ofy;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.UserDetails;

import static dao.UserDetailsDao.hash;

@SuppressWarnings("serial")
public class VerifyPassword extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("uID") == null)
			res.sendRedirect("/loginPage");
		String password = null;
		try {
			password = hash(req.getParameter("pass"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String uID = (String) session.getAttribute("uID");
		UserDetails user = ofy().load().type(UserDetails.class).id(uID).now();
		if (user.getPass().equals(password)) {
			session.setAttribute("verified", "yes");
		} else {
			res.setStatus(403);
		}
	}
}
