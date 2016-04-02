package dao;

import static dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.Ref;

import entity.Test;
import entity.UserDetails;

public class UserDetailsDao {
	static UserDetails ud;

	public static boolean save(String uID, String pass, String name,
			String branch, String college, String source) {
		String id = "1";
		Test test = ofy().load().type(Test.class).id(id).now();
		List<Ref<Test>> temp = new ArrayList<Ref<Test>>();
		temp.add(Ref.create(test));
		UserDetails user = new UserDetails(uID, pass, name, branch, college,
				temp, source);
		ofy().save().entity(user).now();
		ofy().clear();
		return true;
	}

	public static boolean validate(String uID, String pass) {
		boolean res = false;
		List<UserDetails> det = ofy().load().type(UserDetails.class).list();
		Iterator<UserDetails> details = det.iterator();
		while (details.hasNext()) {
			ud = details.next();
			if (ud.getuID().equals(uID) && ud.getPass().equals(pass)) {
				res = true;
				break;
			}
		}
		return res;
	}

	public static boolean check(String uID) {
		boolean res = true;
		List<UserDetails> det = ofy().load().type(UserDetails.class).list();
		Iterator<UserDetails> details = det.iterator();
		while (details.hasNext()) {
			ud = details.next();
			if (ud.getuID().equals(uID)) {
				res = false;
				break;
			}
		}
		return res;
	}

	public static String getuID() {
		return ud.getuID();
	}

	public static String getName() {
		return ud.getName();
	}

	public static String getPass() {
		return ud.getPass();
	}

	public static String getBranch() {
		return ud.getBranch();
	}

	public static String getCollege() {
		return ud.getCollege();
	}

	public static String getSource() {
		return ud.getSource();
	}
}