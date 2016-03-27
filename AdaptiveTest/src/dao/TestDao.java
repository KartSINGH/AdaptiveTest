package dao;

import static dao.OfyService.ofy;

import java.util.List;

import com.googlecode.objectify.Ref;

import entity.Question;
import entity.Test;

public class TestDao {
	public static void saveTest(String id, List<Ref<Question>> question) {
		Test t = new Test(id, question);
		ofy().save().entity(t).now();
		ofy().clear();
	}
}
