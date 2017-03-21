package com.test.Model;

public class Student {
	private int id;
	private String stNo;
	private String stName;
	private String stSex;
	private String stMajor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStNo() {
		return stNo;
	}
	public void setStNo(String stNo) {
		this.stNo = stNo;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public String getStSex() {
		return stSex;
	}
	public void setStSex(String stSex) {
		this.stSex = stSex;
	}
	public String getStMajor() {
		return stMajor;
	}
	public void setStMajor(String stMajor) {
		this.stMajor = stMajor;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", stNo=" + stNo + ", stName=" + stName
				+ ", stSex=" + stSex + ", stMajor=" + stMajor + "]";
	}
	public Student(Integer id, String stNo, String stName, String stSex,
			String stMajor) {
		super();
		this.id = id;
		this.stNo = stNo;
		this.stName = stName;
		this.stSex = stSex;
		this.stMajor = stMajor;
	}
	public Student() {
		super();
	}
}
