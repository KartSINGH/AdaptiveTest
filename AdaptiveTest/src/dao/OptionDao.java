package dao;

import static dao.OfyService.ofy;
import entity.Option;

public class OptionDao {
	public static void saveOption(Option o) {
		ofy().save().entity(o);
		ofy().clear();
	}
}
