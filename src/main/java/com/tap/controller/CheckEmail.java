package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup")
public class CheckEmail  extends HttpServlet
{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		// System.out.println("service() in checkemail servlet");
		 String email= req.getParameter("email");
	     String name=req.getParameter("name");
	     String phoneNumber= req.getParameter("phoneNumber");
	     String password=req.getParameter("password");
	     String cpassword=req.getParameter("cpassword");
	     String address=req.getParameter("address");
	     
	     
	     if(email.length()>=10)
	    	{
	    		HttpSession session=req.getSession();
	    		session.setAttribute("name", name);
	    		session.setAttribute("email", email);
	    		session.setAttribute("phoneNumber", phoneNumber);
	    		session.setAttribute("password", password);
	    		session.setAttribute("cpassword", cpassword);
	    		session.setAttribute("address", address);

	    		resp.sendRedirect("register");
	    		
	    	}
	    	else
	    	{
	    		resp.sendRedirect("invalidEmail.jsp");
	    	}
     }
}
