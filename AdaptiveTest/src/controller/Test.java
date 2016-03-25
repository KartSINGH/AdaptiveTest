package controller;

import static dao.OfyService.ofy;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Ref;

import entity.Option;
import entity.Question;

@SuppressWarnings("serial")
public class Test extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res){
		String id = "2016/03/25 15:21:17Question";
		Question q = ofy().load().type(Question.class).id(id).now();
		List<Ref<Option>> o = q.getOption();
		Iterator<Ref<Option>> it = o.iterator();
		while(it.hasNext()){
			Option op = it.next().get();
			System.out.println(op.getOption());
		}
	}
}
