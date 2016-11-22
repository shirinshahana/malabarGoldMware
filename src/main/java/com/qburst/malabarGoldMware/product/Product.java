package com.qburst.malabarGoldMware.product;
import com.qburst.malabarGoldMware.mapping.product;
import com.qburst.malabarGoldMware.mapping.store;
import com.qburst.malabarGoldMware.util.HibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import java.util.List;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
public class Product  {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void productCatalog(Exchange exchange) throws Exception {
      
        String body = exchange.getIn().getBody(String.class);
        JSONParser parser = new JSONParser();

        JSONObject res =  (JSONObject) parser.parse(body);
        
  
       double current_price = 100;//(assumed to be obtained from server)
        double req_price = Double.parseDouble(res.get("price").toString()) ;
       double weight = req_price /current_price;
       Session session = this.sessionFactory.openSession();

       session.beginTransaction();
       
     
       String sql ="from product where type='"+res.get("product_type").toString()+"' and gender = '"+ res.get("gender").toString()+"' and size = '"+res.get("size").toString() +"' and brand = '"+ res.get("brand").toString()+"' and gold_color = '"+ res.get("gold_color").toString()+"' and weight <= "+ weight;
   	Query q = session.createQuery(sql);
   	List list = q.list();
   	session.getTransaction().commit();
   	String r=list.toString();

       

    
     

      JSONArray json = (JSONArray) parser.parse(r);
      JSONObject result = new JSONObject();
      result.put("success", "true");
      result.put("error_message","");
      result.put("data",json);

     
        exchange.getOut().setBody(result);
    }
    
    
    
    public void productDetails(Exchange exchange) throws Exception {
		Session session = this.sessionFactory.openSession();

        session.beginTransaction();
        
        String body = exchange.getIn().getBody(String.class);
        JSONParser parser = new JSONParser();
        JSONObject res =  (JSONObject) parser.parse(body);
        
      
       product product =(product) session.get(product.class, (Long)res.get("product_id"));   
       
 JSONObject json = new JSONObject();
 json.put("success", "true");
 json.put("data", product);

     
        exchange.getOut().setBody(json);
    }
    
    
}

	
	
	
	
	
	







