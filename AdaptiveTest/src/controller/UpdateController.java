package controller;

import static dao.OfyService.ofy;
import static dao.UserDetailsDao.hash;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.UserDetails;

@SuppressWarnings("serial")
public class UpdateController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("uID") == null)
			res.sendRedirect("/loginPage");
		String pass = req.getParameter("pass");
		try {
			pass = hash(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String uID = (String) session.getAttribute("uID");
		String name = req.getParameter("name");
		UserDetails user = ofy().load().type(UserDetails.class).id(uID).now();
		user.setPass(pass);
		user.setName(name);
		ofy().save().entity(user).now();
		session.setAttribute("name", name);
	}
}
