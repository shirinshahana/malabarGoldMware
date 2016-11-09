package com.qburst.malabar_gold_mware.user.mapping;

import java.io.Serializable;

public class User implements Serializable {
   
    private long id;
    private String username;
    private String password;
    private long phoneNumber;
    private String email;
   
    
    public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
    public String toString() {
        return "User [user_id=" + id + ", username=" + username + ", password="
                + password + ", email="+ email +",phoneNumber="+phoneNumber+"]";
    }           
}