package dao;

import java.util.Iterator;
import java.util.List;

import entity.UserDetails;

import static dao.OfyService.ofy;

public class UserDetailsDao {
	static UserDetails ud;

	public static boolean save(String uID, String pass, String name,
			String branch, String college) {
		UserDetails user = new UserDetails(uID, pass, name, branch, college, null);
		ofy().save().entity(user);
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
}