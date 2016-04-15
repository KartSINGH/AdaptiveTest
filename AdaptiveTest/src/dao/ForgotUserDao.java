package dao;

import static dao.OfyService.ofy;

import java.util.Date;

import com.googlecode.objectify.Ref;

import entity.ForgotUser;
import entity.UserDetails;

public class ForgotUserDao {
	public static void save(String uID, String randomKey, Date date) {
		UserDetails ud = ofy().load().type(UserDetails.class).id(uID).now();
		ForgotUser fu = new ForgotUser(Ref.create(ud), randomKey, date);
		ofy().save().entity(fu).now();
	}

	public static void delete(String uID) {
		ForgotUser fu = ofy().load().type(ForgotUser.class).id(uID).now();
		ofy().delete().entity(fu);
	}
}