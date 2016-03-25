package entity;

import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Question {
	@Id
	String id;
	@Index
	String question;
	String answer;
	List<Ref<Option>> option;
	String course;
	int difficulty;

	public Question() {
		super();
	}

	public Question(String id, String question, String answer,
			List<Ref<Option>> option, String course, int difficulty) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.option = option;
		this.course = course;
		this.difficulty = difficulty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<Ref<Option>> getOption() {
		return option;
	}

	public void setOption(List<Ref<Option>> option) {
		this.option = option;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
