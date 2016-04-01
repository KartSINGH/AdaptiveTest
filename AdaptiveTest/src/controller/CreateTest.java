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
		PrintWriter out = res.getWriter();
		JSONArray jArray = new JSONArray();
		JSONObject temp = new JSONObject();
		int questionCount = 5;
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
			try {
				JSONObject time = new JSONObject();
				JSONObject id = new JSONObject();
				JSONObject no = new JSONObject();
				time.put("time", questionCount * 0.4);
				id.put("testid", test.getId());
				no.put("no", questionCount);
				jArray.put(time);
				jArray.put(id);
				jArray.put(no);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(jArray.toString());
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
			int y = 0 + (int) (Math.random() * 5);
			for (int x = 0; x < y; x++)
				Collections.shuffle(optionList);
			Iterator<Ref<Option>> optionIterator = optionList.iterator();
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
			temp = new JSONObject();
			try {
				temp.put("score", test.getScore());
				jArray.put(temp);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			res.setContentType("application/JSON");
			out.write(jArray.toString());
		}
	}
}
