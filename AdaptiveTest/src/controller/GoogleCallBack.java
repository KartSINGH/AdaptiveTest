package controller;

import static dao.UserDetailsDao.check;
import static dao.UserDetailsDao.save;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entity.GooglePojo;
import entity.UserDetails;

import static dao.OfyService.ofy;

public class GoogleCallBack extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GoogleCallBack() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("entering doGet");
			try {
				// get code
				String code = request.getParameter("code");
				System.out.println(code);
				// format parameters to post
				String urlParameters = "code="
						+ code
						+ "&client_id=585144124880-9rq2hkc3r42lkb0dflmsbj738320ru82.apps.googleusercontent.com"
						+ "&client_secret=s4PuvIRBpYPxCf4Fk-rlDcnE"
						+ "&redirect_uri=http://www.mymock-test.appspot.com/callback"
						+ "&grant_type=authorization_code";

				// post parameters
				URL url = new URL("https://accounts.google.com/o/oauth2/token");
				URLConnection urlConn = url.openConnection();
				urlConn.setDoOutput(true);
				OutputStreamWriter writer = new OutputStreamWriter(
						urlConn.getOutputStream());
				writer.write(urlParameters);
				writer.flush();

				// get output in outputString
				String line, outputString = "";
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(urlConn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
				System.out.println(outputString);

				// get Access Token
				JsonObject json = (JsonObject) new JsonParser()
						.parse(outputString);
				String access_token = json.get("access_token").getAsString();
				System.out.println(access_token);

				// get User Info
				url = new URL(
						"https://www.googleapis.com/oauth2/v1/userinfo?access_token="
								+ access_token);
				urlConn = url.openConnection();
				outputString = "";
				reader = new BufferedReader(new InputStreamReader(
						urlConn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					outputString += line;
				}
				
				// Convert JSON response into Pojo class
				GooglePojo data = new Gson().fromJson(outputString,
						GooglePojo.class);
				// Register
				HttpSession sess = request.getSession();
				// Check ID
				if (check(data.getEmail())) {
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					String id = dateFormat.format(date);
					// Save Data and Login
					save(data.getEmail(), id + "id:" + data.getId(),
							data.getName(), "Branch", "MSIT", "google");
					UserDetails ud = ofy().load().type(UserDetails.class)
							.id(data.getEmail()).now();
					sess.setAttribute("uID", ud.getuID());
					sess.setAttribute("college", ud.getCollege());
					sess.setAttribute("branch", ud.getBranch());
					sess.setAttribute("name", ud.getName());
					sess.setAttribute("source", ud.getSource());
					response.sendRedirect("/user");
				} else {
					// Login
					UserDetails ud = ofy().load().type(UserDetails.class)
							.id(data.getEmail()).now();
					sess.setAttribute("uID", ud.getuID());
					sess.setAttribute("college", ud.getCollege());
					sess.setAttribute("branch", ud.getBranch());
					sess.setAttribute("name", ud.getName());
					sess.setAttribute("source", ud.getSource());
					response.sendRedirect("/user");
				}
				writer.close();
				reader.close();

			} catch (MalformedURLException e) {
				System.out.println(e);
			} catch (ProtocolException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
			System.out.println("leaving doGet");
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Oops. Something went wrong!');");
			out.println("window.location = '/loginPage';");
			out.println("</script>");
		}
	}
}