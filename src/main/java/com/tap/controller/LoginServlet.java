package com.tap.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.UserDAO;
import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login") //getting called from login.jsp
public class LoginServlet extends HttpServlet
{
	
	private PrintWriter pw; 

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserbyemail(email);
		
		pw=resp.getWriter();
	   if((user != null) && password.equals(user.getPassword()))
		{
			HttpSession session=req.getSession();
			session.setAttribute("loggedInUser",user);
			resp.sendRedirect("home"); //calling home page
		}
		else 
		{
            resp.sendRedirect("failure.jsp");
//			req.setAttribute("errorMessage", "Invalid username or password");
//			req.getRequestDispatcher("login.jsp").forward(req,resp);
		} 
		
	}
}
