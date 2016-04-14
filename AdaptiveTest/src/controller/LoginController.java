package controller;

import static dao.OfyService.ofy;
import static dao.UserDetailsDao.hash;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.UserDetails;

@SuppressWarnings("serial")
public class LoginController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		String uID = req.getParameter("email");
		String pass = null;
		try {
			pass = hash(req.getParameter("pass"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		if (ud == null)
			res.setStatus(404);
		else {
			if (ud.getPass().equals(pass)) {
				HttpSession sess = req.getSession();
				sess.setAttribute("uID", ud.getuID());
				sess.setAttribute("college", ud.getCollege());
				sess.setAttribute("branch", ud.getBranch());
				sess.setAttribute("name", ud.getName());
				sess.setAttribute("source", ud.getSource());
				res.sendRedirect("/user");
			} else
				res.setStatus(403);
		}
	}
}
