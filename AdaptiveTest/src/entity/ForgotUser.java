package entity;

import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class ForgotUser {
	@Id
	String randomKey;
	@Index
	Ref<UserDetails> uID;
	Date date;

	public ForgotUser() {
		super();
	}

	public ForgotUser(Ref<UserDetails> uID, String randomKey, Date date) {
		this.uID = uID;
		this.randomKey = randomKey;
		this.date = date;
	}

	public Ref<UserDetails> getuID() {
		return uID;
	}

	public void setuID(Ref<UserDetails> uID) {
		this.uID = uID;
	}

	public String getRandomKey() {
		return randomKey;
	}

	public void setRandomKey(String randomKey) {
		this.randomKey = randomKey;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}