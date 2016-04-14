package controller;

import static dao.UserDetailsDao.check;
import static dao.UserDetailsDao.save;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static dao.UserDetailsDao.hash;

@SuppressWarnings("serial")
public class RegisterController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String name = req.getParameter("name");
		String pass = null;
		try {
			pass = hash(req.getParameter("pass"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String uID = req.getParameter("email");
		String college = "MSIT";
		String branch = req.getParameter("branch");
		if (check(uID)) {
			save(uID, pass, name, branch, college, "form");
		} else {
			res.sendError(403);
		}
	}
}
