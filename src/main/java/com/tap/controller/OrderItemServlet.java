package com.tap.controller;

import com.tap.dao.OrderItemDAO;
import com.tap.daoimpl.OrderItemDAOImpl;
import com.tap.model.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/OrderItemServlet")
public class OrderItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderid"));
        
        System.out.println("Order ID: " + orderId);  // Debugging output
        
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
        ArrayList<OrderItem> orderItemList = orderItemDAO.getOrderItemsByOrderId(orderId);
        
        if (orderItemList != null && !orderItemList.isEmpty()) {
            System.out.println("Order Items found: " + orderItemList.size());  // Debugging output
        } else {
            System.out.println("No Order Items found for order ID: " + orderId);  // Debugging output
        }
        
        // Save order items in the session
        request.getSession().setAttribute("orderItemList", orderItemList);
        
        // Redirect to the JSP page
        response.sendRedirect("displaylist.jsp");
    }

   
}
