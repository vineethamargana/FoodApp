package com.tap.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoimpl.MenuDAOImpl;
import com.tap.model.Menu;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get restaurant ID from the request
        String restaurantId = request.getParameter("restaurantid");
        
        if (restaurantId != null) 
        {
            MenuDAOImpl menuDAO = new MenuDAOImpl();
            ArrayList<Menu> menuList = menuDAO.getAllMenusOnResID(Integer.parseInt(restaurantId));
            
            // Set the menu list in the session
            HttpSession session = request.getSession();
            session.setAttribute("menuList", menuList);
            session.setAttribute("restaurantId", restaurantId);

            // Redirect to menu.jsp using sendRedirect
            response.sendRedirect("menu.jsp");
        }
        else 
        {
            response.sendRedirect("home"); // Redirect to home if restaurantId is null
        }
    }
}
