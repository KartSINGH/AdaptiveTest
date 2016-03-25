package entity;

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
	Option o[];

	public Question() {
		super();
	}

	public Question(String id, String question, String answer, Option[] o) {
		super();
		this.id = id;
		this.question = question;
		this.answer = answer;
		this.o = o;
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

	public Option[] getO() {
		return o;
	}

	public void setO(Option[] o) {
		this.o = o;
	}
}
