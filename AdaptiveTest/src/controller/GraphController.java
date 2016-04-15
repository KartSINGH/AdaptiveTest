package controller;

import static dao.OfyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.objectify.Ref;

import entity.Test;
import entity.UserDetails;

@SuppressWarnings("serial")
public class GraphController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		String uID = (String) session.getAttribute("uID");
		UserDetails user = ofy().load().type(UserDetails.class).id(uID).now();
		Iterator<Ref<Test>> test = user.getTest().iterator();
		JSONArray jArray = new JSONArray();
		while (test.hasNext()) {
			Test temp = test.next().get();
			if (!(temp.getId().equals("1"))) {
				try {
					JSONObject jObject = new JSONObject();
					jObject.put("date", temp.getDate());
					jObject.put("score", temp.getScore());
					jArray.put(jObject);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		PrintWriter out = res.getWriter();
		out.write(jArray.toString());
	}
}
