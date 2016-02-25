package com.brian.app.dto;

public class TestDto1 {
String test;
String username;
String password;


public TestDto1(String test, String username, String password) {
	super();
	this.test = test;
	this.username = username;
	this.password = password;
}

public TestDto1(String username) {
	super();
	this.username = username;
}

public String getUsername() {
	return username;
}


public TestDto1() {
	super();
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

public String getTest() {
	return test;
}

public void setTest(String test) {
	this.test = test;
}


}
