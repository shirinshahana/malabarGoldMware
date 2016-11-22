package com.qburst.malabar_gold_mware.mapping;

public class product {

	private long id;
	private String productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImageSource() {
		return imageSource;
	}
	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	private String size;
	private float weight;
	private String stock_status;
	private String imageSource;
	private String ornament;
	private String productType;
	private String gender;
	private String brand;
	private String gold_color;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getStock_status() {
		return stock_status;
	}
	public void setStock_status(String stock_status) {
		this.stock_status = stock_status;
	}
	
	public String getOrnament() {
		return ornament;
	}
	public void setOrnament(String ornament) {
		this.ornament = ornament;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getGold_color() {
		return gold_color;
	}
	public void setGold_color(String gold_color) {
		this.gold_color = gold_color;
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"type\" : \"").append(productType);
		sb.append("\" ,\"name\" : \"").append(productName);
		sb.append("\" , \"size\" : \"").append(size);
		sb.append("\" , \"brand\" : \"").append(brand);
		sb.append("\" , \"gender\" : \"").append(gender);
		sb.append("\" , \"ornament\" : \"").append(ornament);
		sb.append("\" , \"stock_status\" : \"").append(stock_status);
		sb.append("\" , \"img\" : \"").append(imageSource);
		sb.append("\" , \"id\" : ").append(id);
		sb.append(" , \"gold_color\" : \"").append(gold_color);
		sb.append("\" , \"weight\" : ").append(weight);
		sb.append("}");
		return sb.toString();
	}
	
}
