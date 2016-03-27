package controller;

import static dao.OfyService.ofy;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import entity.Option;
import entity.Question;

@SuppressWarnings("serial")
public class Setup extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		Long id = (long) 1;
		String q = "";
		String a = "";
		String c = "";
		int d = -100;
		Question base = new Question(id, q, a, new ArrayList<Ref<Option>>(), c,
				d);
		ofy().save().entity(base).now();
	}
}
