package controller;

import static dao.OfyService.ofy;
import static dao.TestDao.saveTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import entity.Option;
import entity.Question;
import entity.Test;

@SuppressWarnings("serial")
public class Testing extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		int difficulty = -5;
		for (int i = 0; i < 2; i++) {
			Test test = createTest();
			Question question = getNextQuestion(difficulty, test);
			List<Ref<Question>> usedQuestion = test.getQuestion();
			usedQuestion.add(Ref.create(question));
			test.setQuestion(usedQuestion);
			ofy().save().entity(test).now();
			System.out.println("Q:\t"+question.getQuestion());
			List<Ref<Option>> optionList = question.getOption();
			Collections.shuffle(optionList);
			Iterator<Ref<Option>> optionIterator = optionList.iterator();
			while (optionIterator.hasNext()) {
				Option op = optionIterator.next().get();
				System.out.println(op.getOption());
			}
		}
	}

	public Test createTest() {
		String testId = new Date() + "Test";
		List<Ref<Question>> newQuestion = new ArrayList<Ref<Question>>();
		List<Question> questionList = ofy().load().type(Question.class).list();
		newQuestion.add(Ref.create(questionList.get(0)));
		saveTest(testId, newQuestion);
		Test test = ofy().load().type(Test.class).id(testId).now();
		return test;
	}

	public Question getNextQuestion(int difficulty, Test test) {
		List<Question> questionList = ofy().load().type(Question.class).list();
		Collections.shuffle(questionList);
		Iterator<Question> questionIterator = questionList.iterator();
		while (questionIterator.hasNext()) {
			Question question = questionIterator.next();
			if (question.getDifficulty() > difficulty) {
				while (check(question, test)) {
					question = questionIterator.next();
				}
				if (question != null) {
					return question;
				}
			}
		}
		return getNextQuestion(difficulty, test);
	}

	public boolean check(Question question, Test test) {
		boolean result = false;
		List<Ref<Question>> usedQuestion = test.getQuestion();
		for (int i = 0; i < usedQuestion.size(); i++) {
			Question temp = usedQuestion.get(i).get();
			if (temp.getId() == question.getId()) {
				result = true;
				break;
			}
		}
		return result;
	}
}