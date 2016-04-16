package controller;

import static dao.OfyService.ofy;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.ForgotUser;

@SuppressWarnings("serial")
public class DeleteForgotController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		List<ForgotUser> ls = ofy().load().type(ForgotUser.class).list();
		Iterator<ForgotUser> it = ls.iterator();
		Date date = new Date();
		while (it.hasNext()) {
			ForgotUser fu = it.next();
			if((date.getTime() - fu.getDate().getTime())>=(100*60*30)){
				ofy().delete().entity(fu);
			}
		}
	}
}
