package controller;

import static dao.OfyService.ofy;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.UserDetails;

@SuppressWarnings("serial")
public class UpdateController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("uID") == null)
			res.sendRedirect("/loginPage");
		String password = req.getParameter("pass");
		String uID = (String) session.getAttribute("uID");
		String name = (String) req.getParameter("name");
		UserDetails user = ofy().load().type(UserDetails.class).id(uID).now();
		user.setPass(password);
		user.setName(name);
		ofy().save().entity(user).now();
		session.setAttribute("name", name);
		res.sendRedirect("/user");
	}
}
