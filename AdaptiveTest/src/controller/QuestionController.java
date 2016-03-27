package controller;

import static dao.OptionDao.saveOption;
import static dao.QuestionDao.saveQuestion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import entity.Option;

@SuppressWarnings("serial")
public class QuestionController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String question = req.getParameter("question");
		String answer = req.getParameter("answer");
		String course = req.getParameter("course");
		int difficulty = Integer.parseInt(req.getParameter("difficulty"));
		List<Ref<Option>> option = new ArrayList<Ref<Option>>();
		Option op = new Option(question + "-3" + new Date(), answer);
		saveOption(op);
		option.add(Ref.create(op));
		for (int i = 0; i < 3; i++) {
			op = new Option(question + "-" + i + new Date(),
					req.getParameter("option" + i));
			saveOption(op);
			option.add(Ref.create(op));
		}
		saveQuestion(question, answer, option, course, difficulty);
		res.sendRedirect("Index.jsp");
	}
}
