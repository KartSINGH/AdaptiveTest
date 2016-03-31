package controller;

import static dao.OfyService.ofy;
import static dao.QuestionDao.getNextDifficulty;
import static dao.QuestionDao.getNextQuestion;
import static dao.TestDao.createTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.googlecode.objectify.Ref;

import entity.Option;
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
			session.setAttribute("difficulty", 5);
			out.println(gson.toJson(test.getId()));
		} else {
			int difficulty = (Integer) session.getAttribute("difficulty");
			Test test = ofy().load().type(Test.class).id(testId).now();
			List<Ref<Question>> list = test.getQuestion();
			Question question = list.get(list.size() - 1).get();
			difficulty = getNextDifficulty(question, difficulty, answer, test);
			session.setAttribute("difficulty", difficulty);
			question = getNextQuestion(difficulty, test);
			list.add(Ref.create(question));
			if (list.get(0).get().getId().equals(1))
				list.remove(0);
			test.setQuestion(list);
			ofy().save().entity(test).now();
			List<Ref<Option>> optionList = question.getOption();
			Collections.shuffle(optionList);
			Iterator<Ref<Option>> optionIterator = optionList.iterator();
			JSONArray jArray = new JSONArray();
			JSONObject temp = new JSONObject();
			try {
				temp.put("question", question.getQuestion());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			jArray.put(temp);
			while (optionIterator.hasNext()) {
				temp = new JSONObject();
				try {
					temp.put("option", optionIterator.next().get().getOption());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				jArray.put(temp);
			}
			res.setContentType("application/JSON");
			out.write(jArray.toString());
		}
	}
}
