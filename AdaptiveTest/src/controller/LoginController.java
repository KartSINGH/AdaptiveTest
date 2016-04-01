package controller;

import static dao.UserDetailsDao.getBranch;
import static dao.UserDetailsDao.getCollege;
import static dao.UserDetailsDao.getName;
import static dao.UserDetailsDao.getuID;
import static dao.UserDetailsDao.validate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LoginController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		res.setContentType("text/html");
		String uID = req.getParameter("email");
		String pass = req.getParameter("pass");
		if (validate(uID, pass)) {
			HttpSession sess = req.getSession();
			sess.setAttribute("uID", getuID());
			sess.setAttribute("college", getCollege());
			sess.setAttribute("branch", getBranch());
			sess.setAttribute("name", getName());
			res.sendRedirect("/user");
		} else
			res.sendRedirect("/registerPage");
	}
}
