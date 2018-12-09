package com.fruitsalesplatform.entity;

public class User {
	private String userId;
	private String userName;
	private String password;
	private String name;
	private String telephone;
	private String qqOpenId;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getQQOpenId() {
		return qqOpenId;
	}
	
	public void setQQOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}
}
