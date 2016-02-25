package com.brian.app.dto;

public class TestDto {
String username;
String password;
String image;
String hello;


public String getHello() {
	return hello;
}
public void setHello(String hello) {
	this.hello = hello;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
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
public TestDto(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}
public TestDto() {
	super();
}

}
