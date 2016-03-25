package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Test {
	@Id
	String id;
	@Index
	Question question[];

	public Test() {
		super();
	}

	public Test(String id, Question[] question) {
		super();
		this.id = id;
		this.question = question;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Question[] getQuestion() {
		return question;
	}

	public void setQuestion(Question[] question) {
		this.question = question;
	}

}
