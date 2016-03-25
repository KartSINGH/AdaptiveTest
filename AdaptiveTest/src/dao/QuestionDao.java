package dao;

import static dao.OfyService.ofy;

import java.util.List;

import com.googlecode.objectify.Ref;

import entity.Option;
import entity.Question;

public class QuestionDao {
	public static void saveQuestion(String id, String question, String answer,
			List<Ref<Option>> option, String course) {
		Question q = new Question(id, question, answer, option, course);
		ofy().save().entity(q);
		ofy().clear();
	}
}
