package com.tap.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoimpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

@WebServlet("/home") //getting called from loginservlet
public class Home extends HttpServlet
{
   private RestaurantDAOImpl restaurantDAO;

@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
	   restaurantDAO = new RestaurantDAOImpl(); //creating object for restaurantDAOImpl using restaurantDAO interface
	   ArrayList<Restaurant> restaurantList = restaurantDAO.getAllRestaurants(); //we can perform this operation in login servlet or in this home page as our wish.
	   HttpSession session = req.getSession();
	   session.setAttribute("restaurantList", restaurantList); //adding the restaurant list in session.
	   resp.sendRedirect("home.jsp"); //calling home.jsp
    }
}
