package com.qburst.malabarGoldMware.store;

import java.util.List;

import org.apache.camel.Exchange;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.qburst.malabarGoldMware.mapping.store;
import com.qburst.malabarGoldMware.util.HibernateUtil;
public class Store{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	public void storeLocator(Exchange exchange) throws Exception {
    	String body = exchange.getIn().getBody(String.class);
        JSONParser parser = new JSONParser();
        JSONObject res =  (JSONObject) parser.parse(body);
        Session session = this.sessionFactory.openSession();

	    session.beginTransaction();
	    String sql = "from store where country='"+res.get("country").toString()+"' and city='"+res.get("city").toString()+"' and state='"+res.get("state").toString()+"' and longitude='"+res.get("longitude").toString()+"' and latitude='"+ res.get("latitude").toString()+"'";
		Query q = session.createQuery(sql);
		
		List list = q.list();
		

	

		session.getTransaction().commit();
		
		 String r = list.toString();
        
       
        JSONArray json = (JSONArray) parser.parse(r);
           
        exchange.getOut().setBody(json);

    }
}
