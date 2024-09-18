package com.tap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dbutils.DBUtils;

@WebServlet("/register")
public class UserRegister extends HttpServlet
{
	
	private Connection con;
	private PrintWriter pw;
	private PreparedStatement pstmt;
//	private static String url = "jdbc:mysql://localhost:3306/tapfoods1";
//	private static String username = "root";
//	private static String passwor = "Vineetha48@";
   
	public UserRegister()
	{
		try {
        	 con = DBUtils.myconnect();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
	}
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
       System.out.println("service() in register servlet");
 	   HttpSession session=req.getSession();
 	   String name=(String) session.getAttribute("name");
 	   String email=(String) session.getAttribute("email");
 	   String password=(String) session.getAttribute("password");
	   String cpassword=(String) session.getAttribute("cpassword");
 	   String address=(String) session.getAttribute("address");
 	   String phoneNumber1=(String) session.getAttribute("phoneNumber");
       Long phoneNumber = Long.parseLong(phoneNumber1);
 	  
       pw=resp.getWriter();
 	   String sql="insert into `user` (`username`,`email`,`phonenumber`,`password`,`address`) values(?,?,?,?,?) ";
 
 	  try 
 	   {
 			 
 	    if(password.equals(cpassword))
 		{	 
// 	    	 UserDAOImpl ui=new UserDAOImpl();
// 	 	       int x=ui.addUser(u);
 		    pstmt=con.prepareStatement(sql);
 		    
 		    pstmt.setString(1, name);
 		    pstmt.setString(2, email);
 		    pstmt.setString(4, password);
  		   	pstmt.setString(5, address );
  		   	pstmt.setLong(3, phoneNumber);
  		  
 		  
 		  int x=pstmt.executeUpdate(); 	  
 	      if(x!=0)
 	      {
 	    	  resp.sendRedirect("successregister.jsp");
 	      }
 	      else
 	      {
 	    	  resp.sendRedirect("failureregister.jsp");
 	      }
 		 }
 	      else
 	      {
 	    	  resp.sendRedirect("invalidpassword.jsp");
 	      }
 	   } 
 	   catch (Exception e) 
 		{
 		   pw.println("some Exception");
 			e.printStackTrace();
 		}
 	  
 	  
    }
}
