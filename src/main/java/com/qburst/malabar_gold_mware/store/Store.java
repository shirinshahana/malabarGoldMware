package com.qburst.malabar_gold_mware.store;

import java.util.List;

import org.apache.camel.Exchange;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.qburst.malabar_gold_mware.mapping.store;
import com.qburst.malabar_gold_mware.util.HibernateUtil;
public class Store{
	
	public void storeLocator(Exchange exchange) throws Exception {
    	String body = exchange.getIn().getBody(String.class);
        JSONParser parser = new JSONParser();
        JSONObject res =  (JSONObject) parser.parse(body);
        StoreMethods pm = new StoreMethods();
        //pm.addStore("MGD", "Kannur", "Kerala", "India", "jdsufgdgbhhye",(float)54.54,(float) 42.55, "wtlkjhhrejglekw");
        String r = pm.getStoreDetails(res.get("country").toString(), res.get("state").toString(),res.get("city").toString(),Double.parseDouble( res.get("longitude").toString()),Double.parseDouble(  res.get("latitude").toString()));

        JSONArray json = (JSONArray) parser.parse(r);
           
        exchange.getOut().setBody(json);

    }
}
class StoreMethods{
	public void addStore(
			 String name,
			 

			 
			 String city,
			 String state,
			 String country,
			 String location,
			 float latitude,
			 float longitude,
			 String address
){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
		store bd = new store();
		
		bd.setName(name);
		bd.setCountry(country);
		bd.setCity(city);
		bd.setState(state);
		bd.setLocation(location);
		bd.setLatitude(latitude);
		bd.setLongitude(longitude);
		bd.setAddress(address);



		session.save(bd);
		session.getTransaction().commit();
	}
	
	public String getStoreDetails(String country,String state,String city,double longitude,double latitude  ){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    String sql = "from store where country='"+country+"' and city='"+city+"' and state='"+state+"' and longitude='"+longitude+"' and latitude='"+latitude+"'";
		Query q = session.createQuery(sql);
		
		List list = q.list();
		

	

		session.getTransaction().commit();
		
		return list.toString();

	}
}