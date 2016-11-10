package com.qburst.malabar_gold_mware;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class App implements Processor {
    public void process(Exchange exchange) throws Exception {
        // just get the body as a string
//    	int i;
//    	String id = "";
    	String id, name, img;
        String body = exchange.getIn().getBody(String.class);
        JSONParser parser = new JSONParser();
//        JSONArray json = (JSONArray) parser.parse(body);
        System.out.println(body);
        JSONObject res =  (JSONObject) parser.parse(body);
        String token=res.get("token").toString();
        
      
//    for(i=0;i<json.size();i++)
//        {
//        	
//     	res = (JSONObject) json.get(i);
//     	id = id + (String) res.get("_id") + "<br />";
//     	
////     			System.out.println(res.get("_id"));
//        }
       double current_price = 100;//(assumed to be obtained from server)
        double req_price = Double.parseDouble(res.get("price").toString()) ;
       double weight = req_price /current_price;
        Statement stmt = null;
        JSONObject reslt = new JSONObject() ;
        JSONArray result = new JSONArray();
        java.sql.Connection c = null;
       if(token.length()!=0){
       try {
          Class.forName("org.postgresql.Driver");
          c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=qburst");
          stmt = c.createStatement();
          String query = "SELECT product_id FROM productCategory where product_type = '"+res.get("product_type")+"' and gender = '"+ res.get("gender")+"' and brand = '"+ res.get("brand")+"' and gold_color = '"+ res.get("gold_color")+"';";
          System.out.println(query);
          ResultSet rs = stmt.executeQuery( query );
         
          while ( rs.next() ) {
        	  
            reslt.put("product_id", rs.getString("product_id"));
//            reslt.put("product_name", rs.getString("name"));
//            reslt.put("product_image_url", rs.getString("img"));
             
             result.add(reslt);
             
          }
          rs.close();
          stmt.close();
          c.close();
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
       }
        }
        else{
        	reslt.put("Status", "404");
        	reslt.put("message", "Unautherised access");
           result.add(reslt);
        }

        // we have access to the HttpServletRequest here and we can grab it if we need to
        //HttpServletRequest req = exchange.getIn().getBody(HttpServletRequest.class);
        //assertNotNull(req);

        // for unit testing
        //assertEquals("bookid=123", body);

        exchange.getOut().setBody(result);
    }
    public String getProductDeatails(Exchange exchange){
    	String body=exchange.getIn().getBody(String.class);
    	JSONParser parser=new JSONParser();
    	JSONObject result;
		try {
			result = (JSONObject) parser.parse(body);
	    	Double productId=Double.parseDouble(result.get("product_id").toString()) ;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    	
    }
}