package dao;

import static dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;

import entity.Question;
import entity.Test;

public class TestDao {
	public static void saveTest(String id, List<Ref<Question>> question,
			Date date) {
		Test t = new Test(id, question, 0, date);
		ofy().save().entity(t).now();
		ofy().clear();
	}

	public static Test createTest() {
		String testId = new Date() + "Test";
		List<Ref<Question>> newQuestion = new ArrayList<Ref<Question>>();
		Long id = (long) 1;
		Question temp = ofy().load().type(Question.class).id(id).now();
		newQuestion.add(Ref.create(temp));
		saveTest(testId, newQuestion, new Date());
		Test test = ofy().load().type(Test.class).id(testId).now();
		return test;
	}

}
