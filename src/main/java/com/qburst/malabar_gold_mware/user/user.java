package com.qburst.malabar_gold_mware.user;

import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.camel.Exchange;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.mapping.List;

import java.security.SecureRandom;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.qburst.malabar_gold_mware.mapping.User;
import com.qburst.malabar_gold_mware.util.HibernateUtil;

public class user {
	public void userRegistration(Exchange exchange) throws ParserConfigurationException, ParseException {
		String body = exchange.getIn().getBody(String.class);
		JSONParser parser = new JSONParser();
		Statement stmt = null;
		JSONObject reslt = new JSONObject();
		JSONArray result = new JSONArray();
		java.sql.Connection c = null;
		JSONObject res;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		res = (JSONObject) parser.parse(body);
		String username = res.get("username").toString();
		String password = res.get("password").toString();
		String email = res.get("email").toString();
		Long phoneNumber = (Long) res.get("phoneNumber");

		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setEmail(email);
		u.setPhoneNumber(phoneNumber);
		long userId = (Long) session.save(u);
		session.getTransaction().commit();

		User users = (User) session.get(User.class, userId);
		reslt.put("message", "Registration succesfull");
		reslt.put("status", "200");
		result.add(reslt);
		exchange.getOut().setBody(result);

		session.close();

	}

	public void getUserInfo(Exchange exchange) throws ParseException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String body = exchange.getIn().getBody(String.class);
		Long userID = null;
		JSONParser parser = new JSONParser();
		JSONArray result = new JSONArray();
		JSONObject reslt = new JSONObject();
		JSONObject res;
		if (body.length() == 0) {
			String hql = "FROM User";
			Query query = session.createQuery(hql);
			List results = query.list();
			String users = new Gson().toJson(results);
			Object json = parser.parse(users);
			reslt.put("data", json);
		} else {
			res = (JSONObject) parser.parse(body);
			userID = (Long) res.get("user_id");

			User user = new User();
			user = (User) session.get(User.class, userID);
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			String token = bytes.toString();
			reslt.put("user", user);
			reslt.put("token", token);
		}
		result.add(reslt);

		exchange.getOut().setBody(result);
	}

	public void loginUser(Exchange exchange) throws ParseException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String body = exchange.getIn().getBody(String.class);
		JSONParser parser = new JSONParser();
		JSONObject res = (JSONObject) parser.parse(body);
		JSONArray result = new JSONArray();
		JSONObject reslt = new JSONObject();

		String username = (String) res.get("username").toString();
		String password = (String) res.get("password").toString();
		String hql = "FROM User u WHERE u.username = '" + username + "' and u.password='" + password + "'";
		Query query = session.createQuery(hql);
		List resultd = (List) query.list();
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		String token = bytes.toString();
		String json = new Gson().toJson(resultd);
		reslt.put("status", "success");
		reslt.put("token", token);
		reslt.put("data", json);
		result.add(reslt);
		exchange.getOut().setBody(result);
	}
}