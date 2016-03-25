package entity;

import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Test {
	@Id
	Long id;
	@Index
	List<Ref<Question>> question;
	int score;

	public Test() {
		super();
	}

	public Test( List<Ref<Question>> question, int score) {
		super();
		this.question = question;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
}
