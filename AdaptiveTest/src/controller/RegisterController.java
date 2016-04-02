package controller;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

import static dao.UserDetailsDao.save;
import static dao.UserDetailsDao.check;

@SuppressWarnings("serial")
public class RegisterController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String uID = req.getParameter("email");
		String college = "MSIT";
		String branch = req.getParameter("branch");
		if (check(uID)) {
			if (save(uID, pass, name, branch, college,"form")) {
				res.sendRedirect("/loginPage");
			} else
				res.sendRedirect("/registerPage");
		} else {
			res.sendRedirect("/dreg.html");
		}
	}
}
