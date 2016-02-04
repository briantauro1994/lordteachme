package com.brian.app.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	long pkey;
	@Column(name = "customer_name")
	String username;
	@Column(name = "customer_password")
	String password;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	

	public User() {
		super();
	}





	public long getPkey() {
		return pkey;
	}





	public void setPkey(long pkey) {
		this.pkey = pkey;
	}





	public User(long pkey, String username, String password) {
		super();
		this.pkey = pkey;
		this.username = username;
		this.password = password;
	}

	

}
