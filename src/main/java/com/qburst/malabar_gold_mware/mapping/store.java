
package com.qburst.malabar_gold_mware.mapping;

public class store {

	private int id;
	private String name;
	private String country;
	private float latitude;
	private float longitude;
	private String city;
	private String state;
	private String location;
	private String address;
	private String gender;
	private String brand;
	private String gold_color;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"id\" : \"").append(id);
		sb.append("\" ,\"name\" : \"").append(name);
		sb.append("\" , \"country\" : \"").append(country);
		sb.append("\" , \"state\" : \"").append(state);
		sb.append("\" , \"city\" : \"").append(city);
		sb.append("\" , \"address\" : \"").append(address);
		sb.append("\" , \"location\" : \"").append(location);
		sb.append("\" , \"latitude\" : \"").append(latitude);

		sb.append("\" , \"longitude\" : ").append(longitude);
		sb.append("}");
		return sb.toString();
	}
	
}
