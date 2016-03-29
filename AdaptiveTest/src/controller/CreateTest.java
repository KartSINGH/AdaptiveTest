package controller;

import static dao.OfyService.ofy;
import static dao.QuestionDao.getNextDifficulty;
import static dao.QuestionDao.getNextQuestion;
import static dao.TestDao.createTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.googlecode.objectify.Ref;

import entity.Question;
import entity.Test;
import entity.UserDetails;

@SuppressWarnings("serial")
public class CreateTest extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpSession session = req.getSession();
		String uID = (String) session.getAttribute("uID");
		String testId = req.getParameter("test");
		int difficulty = Integer.parseInt(req.getParameter("difficulty"));
		String answer = req.getParameter("answer");
		Gson gson = new Gson();
		PrintWriter out = res.getWriter();
		if (testId == null) {
			Test test = createTest();
			UserDetails user = ofy().load().type(UserDetails.class).id(uID)
					.now();
			List<Ref<Test>> list = user.getTest();
			list.add(Ref.create(test));
			if (list.get(0).get().getId().equals("1"))
				list.remove(0);
			user.setTest(list);
			ofy().save().entity(user).now();
			out.println(gson.toJson(test));
		} else {
			Test test = gson.fromJson(testId, Test.class);
			List<Ref<Question>> list = test.getQuestion();
			Question question = list.get(list.size() - 1).get();
			difficulty = getNextDifficulty(question, difficulty, answer, test);
			question = getNextQuestion(difficulty, test);
			out.println(gson.toJson(question));
		}
	}
}
