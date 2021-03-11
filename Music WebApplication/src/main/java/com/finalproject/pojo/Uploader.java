package com.finalproject.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Uploader")
public class Uploader {
	
	public Uploader() {
		// TODO Auto-generated constructor stub
	}
	
	public Uploader (String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UploaderId", nullable = false)
	private int id;

	@Column(name = "FirstName", nullable = false)
	private String firstName;
	
	@Column(name = "LastName", nullable = false)
	private String lastName;
	
	@Column(name = "UserName", nullable = false)
	private String userName;
	
	@Column(name = "Password", nullable = false)
	private String password;
	
	@Id
	@Column(name = "Email", unique = true, nullable = false)
	private String email;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
