package com.test.Dao;

import java.util.List;

import com.test.Model.User;
public interface IUserDao {
	List<User> getAllList();
	User getById(int id);
	int addUser(User user);
	int updateUser(User user);
	int delUser(int id);
	User Checklogin(String userCode, String passWord);
	User Register(String userCode, String passWord);
}
