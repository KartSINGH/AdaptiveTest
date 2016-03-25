package entity;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Test {
	@Id
	String id;
	@Index
	List<Question> question;
	int score;

	public Test() {
		super();
	}

	public Test(String id, int score) {
		super();
		this.id = id;
		this.question = null;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
