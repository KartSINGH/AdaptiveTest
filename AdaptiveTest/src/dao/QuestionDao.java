package dao;

import static dao.OfyService.ofy;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Ref;

import entity.Option;
import entity.Question;
import entity.Test;

public class QuestionDao {
	public static void saveQuestion(String question, String answer,
			List<Ref<Option>> option, String course, int difficulty) {
		Question q = new Question(question, answer, option, course, difficulty);
		ofy().save().entity(q);
		ofy().clear();
	}

	public static Question getNextQuestion(int difficulty, Test test) {
		List<Question> questionList = ofy().load().type(Question.class).list();
		int y = 0 + (int) (Math.random() * 5);
		for (int x = 0; x < y; x++)
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

	public static boolean check(Question question, Test test) {
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

	public static int getNextDifficulty(Question question, int difficulty,
			String answer, Test test) {
		if (answer == null) {
			return difficulty;
		} else {
			if (question.getAnswer().equals(answer)) {
				difficulty++;
				test.setScore(test.getScore() + 1);
				ofy().save().entity(test).now();
			} else
				difficulty--;
			return difficulty;
		}
	}
}
