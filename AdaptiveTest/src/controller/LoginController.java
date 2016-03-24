package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import static dao.UserDetailsDao.validate;
import static dao.UserDetailsDao.getuID;
import static dao.UserDetailsDao.getName;
import static dao.UserDetailsDao.getBranch;
import static dao.UserDetailsDao.getCollege;
import static dao.UserDetailsDao.getPass;

@SuppressWarnings("serial")
public class LoginController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		res.setContentType("text/html");
		String uID = req.getParameter("email");
		String pass = req.getParameter("pass");
		if (validate(uID, pass)) {
			System.out.println("Valid");
			HttpSession sess = req.getSession();
			sess.setAttribute("uID", getuID());
			sess.setAttribute("college", getCollege());
			sess.setAttribute("branch", getBranch());
			sess.setAttribute("name", getName());
			sess.setAttribute("pass", getPass());
			System.out.println(uID);
			res.sendRedirect("user.html");
		} else
			res.sendRedirect("reg.html");
	}
}
