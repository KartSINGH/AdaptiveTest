package dao;

import static dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;

import entity.Test;
import entity.UserDetails;

import java.security.*;
import java.math.*;

public class UserDetailsDao {
	static UserDetails ud;

	public static boolean save(String uID, String pass, String name, String branch, String college, String source) {
		String id = "1";
		Test test = ofy().load().type(Test.class).id(id).now();
		List<Ref<Test>> temp = new ArrayList<Ref<Test>>();
		temp.add(Ref.create(test));
		UserDetails user = new UserDetails(uID, pass, name, branch, college, temp, source);
		ofy().save().entity(user).now();
		ofy().clear();
		return true;
	}

	public static boolean check(String uID) {
		boolean res = true;
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		if (ud != null)
			res = false;
		return res;
	}

	public static String hash(String pass) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(pass.getBytes(), 0, pass.length());
		return (new BigInteger(1, md5.digest()).toString(16));
	}
}