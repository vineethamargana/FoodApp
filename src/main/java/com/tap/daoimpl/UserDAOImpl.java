package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tap.dao.UserDAO;
import com.tap.dbutils.DBUtils;
import com.tap.model.User;

public class UserDAOImpl implements UserDAO
{
	Connection con;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	ArrayList<User> userList = new ArrayList<User>();
	User user;
	 	
	
	private static final String ADD_USER ="insert int `user` (`username`,`email`,`phonenumber`,`password`,`address`) values (?,?,?,?,?)"; //incomplete query
	
	private static final String SELECT_ALL = "select * from `user` "; //complete query
	
	private static final String SELECT_ON_EMAIL = "select * from `user` where `email` = ? "; //getting user based on email
	
	private static final String UPDATE_ON_EMAIL = "update `user` set `username` =? ,`phonenumber` = ? , `password` = ?," +" `address`= ? where `email` = ? "; //updating all the info of user based on email
	
	private static final String DELETE_ON_EMAIL = "delete from `user` where `email` = ? "; //updating all the info of user based on email

	private static final String updateQuery = "UPDATE users SET username = ?, address = ?, phone = ? WHERE userid = ?";
	  
	int status=0;
	public UserDAOImpl() 
	{
		try 
		{
			con = DBUtils.myconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	
	@Override
	public int addUser(User u) {
		
		try 
		{
			pstmt = con.prepareStatement(ADD_USER);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2,u.getEmail());
			pstmt.setString(3, u.getPhonenumber());
			pstmt.setString(4, u.getPassword());
			pstmt.setString(5, u.getAddress());
			
		    status =pstmt.executeUpdate();  //executeUpdate because we are using insert command
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	
	@Override
	public ArrayList<User> getAllUsers() 
	{
		try 
		{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(SELECT_ALL);
			userList = extractUserFromResultSet(resultSet);
//			
//			while(resultSet.next())
//			{
//				userList.add(new User (resultSet.getInt("userId"),
//				resultSet.getString("username"),
//				resultSet.getString("email"),
//				resultSet.getString("phonenumber"),
//				resultSet.getString("password"),
//				resultSet.getString("address"))); 
//				
//			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return userList;
	}
	
	
	
	@Override
	public User getUserbyemail(String email) 
	{
		try 
		{
		  pstmt = con.prepareStatement(SELECT_ON_EMAIL);	
		  pstmt.setString(1, email);	  
		  resultSet = pstmt.executeQuery();  //executeQuery because we are using select command
		  userList = extractUserFromResultSet(resultSet);
		  user = userList.get(0);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	@Override
	public int updateUserr(User u)
	{
		try 
		{
			pstmt = con.prepareStatement(UPDATE_ON_EMAIL);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPhonenumber());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			pstmt.setString(5, u.getEmail());
			
			status = pstmt.executeUpdate();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}
	
		
	
	@Override
	public int deleteUser(String email) 
	{
		try 
		{
			 pstmt = con.prepareStatement(DELETE_ON_EMAIL);	
			 pstmt.setString(1, email);
			 
			 status = pstmt.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public boolean updateUser(User user) {
	   // String updateQuery = "UPDATE users SET username = ?, address = ?, phone = ? WHERE userid = ?";
	    try {
	        pstmt = con.prepareStatement(updateQuery);
	        pstmt.setString(1, user.getUsername());
	        pstmt.setString(2, user.getAddress());
	        pstmt.setString(3, user.getPhonenumber());
	        pstmt.setInt(4, user.getUserId());

	        int rowsUpdated = pstmt.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	ArrayList<User> extractUserFromResultSet(ResultSet resultSet)
	{

		try 
		{
			while(resultSet.next())
			{
				userList.add(new User (resultSet.getInt("userId"),
				resultSet.getString("username"),
				resultSet.getString("email"),
				resultSet.getString("phonenumber"),
				resultSet.getString("password"),
				resultSet.getString("address"))); 
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return userList;
	}

}
