package controller;

import static dao.OfyService.ofy;
import static dao.TestDao.saveTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import entity.Option;
import entity.Question;

@SuppressWarnings("serial")
public class Setup extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = (long) 1;
		String q = "";
		String a = "";
		String c = "";
		int d = -100;
		Question baseTest = new Question(id, q, a, new ArrayList<Ref<Option>>(), c, d);
		ofy().save().entity(baseTest).now();

		String testId = "1";
		List<Ref<Question>> newQuestion = new ArrayList<Ref<Question>>();
		newQuestion.add(Ref.create(baseTest));
		saveTest(testId, newQuestion, new Date());
		PrintWriter out = res.getWriter();
		out.println("<script>alert('Setup Complete. Please get back to the main site.')\nwindow.open('/','_self')</script>");
	}
}
