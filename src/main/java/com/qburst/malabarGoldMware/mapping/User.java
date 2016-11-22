package com.qburst.malabarGoldMware.mapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "userss")
public class User  {
	 @Id @GeneratedValue
	   @Column(name = "user_id")
    private long id;
	 @Column(name = "username")
    private String username;
	 @Column(name = "password")
    private String password;
	 @Column(name = "phoneNumber")
    private long phoneNumber;
	 @Column(name = "email")
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


