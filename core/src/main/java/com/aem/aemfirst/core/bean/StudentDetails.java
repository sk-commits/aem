package com.aem.aemfirst.core.bean;

public class StudentDetails {
private String firstName;
private String lastName;
private String email;



@Override
public String toString() {
	return "Student [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
	
	
}
