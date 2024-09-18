package com.tap.dao;

import java.util.ArrayList;

import com.tap.model.User;

public interface UserDAO 
{
	int addUser(User u);
	ArrayList<User> getAllUsers();
	User getUserbyemail(String email);
	int updateUserr(User u);
	int deleteUser(String email);
	boolean updateUser(User user);

}
