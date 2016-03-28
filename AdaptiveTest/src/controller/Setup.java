package controller;

import static dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import entity.Option;
import entity.Question;
import entity.Test;

@SuppressWarnings("serial")
public class Setup extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		Long id = (long) 1;
		String q = "";
		String a = "";
		String c = "";
		int d = -100;
		Question baseTest = new Question(id, q, a,
				new ArrayList<Ref<Option>>(), c, d);
		ofy().save().entity(baseTest).now();

		String testId = "1";
		List<Ref<Question>> newQuestion = new ArrayList<Ref<Question>>();
		newQuestion.add(Ref.create(baseTest));
		Test test = new Test(testId, newQuestion);
		ofy().save().entity(test).now();
	}
}
