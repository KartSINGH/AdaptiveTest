package dao;

import static dao.OfyService.ofy;

import java.util.List;

import com.googlecode.objectify.Ref;

import entity.Option;
import entity.Question;

public class QuestionDao {
	public static void saveQuestion(String question, String answer,
			List<Ref<Option>> option, String course, int difficulty) {
		Question q = new Question(question, answer, option, course, difficulty);
		ofy().save().entity(q);
		ofy().clear();
	}
}
