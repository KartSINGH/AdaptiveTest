package entity;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Test {
	@Id
	String id;
	List<Ref<Question>> question;
	int score;
	@Index
	Date date;

	public Test() {
		super();
	}

	public Test(String id, List<Ref<Question>> question, int score, Date date) {
		super();
		this.id = id;
		this.question = question;
		this.score = score;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Ref<Question>> getQuestion() {
		return question;
	}

	public void setQuestion(List<Ref<Question>> question) {
		this.question = question;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
