package controller;

import static dao.OfyService.ofy;
import static dao.UserDetailsDao.hash;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import entity.ForgotUser;
import entity.UserDetails;

@SuppressWarnings("serial")
public class UpdatePasswordController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String uID = req.getParameter("email");
		String pass = req.getParameter("pass");
		try {
			pass = hash(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserDetails user = ofy().load().type(UserDetails.class).id(uID).now();
		List<ForgotUser> ls = ofy().load().type(ForgotUser.class).filter("uID", Ref.create(user)).list();
		ofy().delete().entities(ls);
		user.setPass(pass);
		user.setSource("form");
		ofy().save().entity(user).now();
	}
}
