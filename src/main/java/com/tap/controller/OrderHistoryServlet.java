package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoimpl.OrderHistoryDAOImpl;
import com.tap.model.OrderHistory;
import com.tap.model.User;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the logged-in user from the session
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Fetch the order history details for the logged-in user
            OrderHistoryDAOImpl orderHistoryDAO = new OrderHistoryDAOImpl();
            List<OrderHistory> orderHistoryList = orderHistoryDAO.getOrderHistoryByUserId(loggedInUser.getUserId());

            // Set order history in session
            session.setAttribute("orderHistoryList", orderHistoryList);

            // Forward to orderhistory.jsp
            request.getRequestDispatcher("orderhistory.jsp").forward(request, response);
        } else {
            // If the user is not logged in, redirect to the login page
            response.sendRedirect("login.jsp");
        }
    }
}
