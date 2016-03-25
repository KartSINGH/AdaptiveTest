package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserDetails {
	@Id
	String uID;

	@Index
	String pass;
	String name;
	String college;
	String branch;
	Test test[];

	public UserDetails() {
		super();
	}

	public UserDetails(String uID, String pass, String name, String college,
			String branch, Test[] test) {
		super();
		this.uID = uID;
		this.pass = pass;
		this.name = name;
		this.college = college;
		this.branch = branch;
		this.test = test;
	}

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Test[] getTest() {
		return test;
	}

	public void setTest(Test[] test) {
		this.test = test;
	}

}
