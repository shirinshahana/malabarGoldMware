package com.qburst.malabar_gold_mware.product;
import com.qburst.malabar_gold_mware.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.qburst.malabar_gold_mware.mapping.product;
import com.qburst.malabar_gold_mware.mapping.store;

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
    public void productCatalog(Exchange exchange) throws Exception {
      
        String body = exchange.getIn().getBody(String.class);
        JSONParser parser = new JSONParser();

        JSONObject res =  (JSONObject) parser.parse(body);
        
      
  
       double current_price = 100;//(assumed to be obtained from server)
        double req_price = Double.parseDouble(res.get("price").toString()) ;
       double weight = req_price /current_price;

       ProductMethods pm = new ProductMethods();
       //pm.addProduct(1234, "sakjudyfgki", "medium", (float) 1.5, "available", "jdsufgdgbhhye", "bangle", "gold", "female", "malabar", "yellow");
       String r = pm.getProductDetails(res.get("product_type").toString(),res.get("gender").toString(),res.get("size").toString(),res.get("gold_color").toString(),res.get("stock_status").toString(),res.get("brand").toString(),weight); 
      System.out.println(r);
      JSONArray json = (JSONArray) parser.parse(r);
      JSONObject result = new JSONObject();
      result.put("success", "true");
      result.put("error_message","");
      result.put("data",json);

     
        exchange.getOut().setBody(result);
    }
    
    
    
    public void productDetails(Exchange exchange) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        String body = exchange.getIn().getBody(String.class);
        JSONParser parser = new JSONParser();
        JSONObject res =  (JSONObject) parser.parse(body);
        
       ProductMethods pm = new ProductMethods();
       product product =(product) session.get(product.class, (Long)res.get("product_id"));   
       
 JSONObject json = new JSONObject();
 json.put("success", "true");
 json.put("data", product);

     
        exchange.getOut().setBody(json);
    }
    
    
}
class ProductMethods{
	
	public void addProduct(int id,
			 String name,
			 String size,
			 float weight,
			 String stock_status,
			 String img,
			 String ornament,
			 String type,
			 String gender,
			 String brand,
			 String gold_color){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
		
		product bd = new product();
		bd.setId(id);
		bd.setName(name);
		bd.setSize(size);
		bd.setWeight(weight);
		bd.setStock_status(stock_status);
		bd.setImg(img);
		bd.setOrnament(ornament);
		bd.setType(type);
		bd.setGender(gender);
		bd.setBrand(brand);
		bd.setGold_color(gold_color);


		session.save(bd);
		session.getTransaction().commit();
	}

	
	
	
	
	
public String getProductDetails(String type, String gender,String size, String gold_color, String stock_status, String brand, double weight ){
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    String sql ="from product where type='"+type+"' and gender = '"+ gender+"' and size = '"+size +"' and brand = '"+ brand+"' and gold_color = '"+ gold_color+"' and weight <= "+ weight;
	Query q = session.createQuery(sql);
	List list = q.list();
	session.getTransaction().commit();
	return list.toString();

}



}



