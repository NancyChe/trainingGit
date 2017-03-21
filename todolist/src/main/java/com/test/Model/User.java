package com.test.Model;

import java.io.Serializable;

public class User implements Serializable{
	private Integer id;
	private String name;
	private String userCode;
	private String passWord;
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getUserCode() {
		return userCode;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userCode=" + userCode
				+ ", passWord=" + passWord + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String name, String userCode, String passWord) {
		super();
		this.id = id;
		this.name = name;
		this.userCode = userCode;
		this.passWord = passWord;
	}
	
	
}
