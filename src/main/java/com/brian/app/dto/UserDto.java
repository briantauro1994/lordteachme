package com.brian.app.dto;

public class UserDto {
String customername;
String customerpassword;
public String getCustomername() {
	return customername;
}
public void setCustomername(String customername) {
	this.customername = customername;
}
public String getCustomerpassword() {
	return customerpassword;
}
public void setCustomerpassword(String customerpassword) {
	this.customerpassword = customerpassword;
}
public UserDto(String customername, String customerpassword) {
	super();
	this.customername = customername;
	this.customerpassword = customerpassword;
}
public UserDto() {
	super();
}

}
