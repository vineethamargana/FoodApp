package com.tap.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tap.dbutils.DBUtils;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.User;

@WebServlet("/confirmOrder")
public class OrderConfirmationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve session and user information
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        Cart cart = (Cart) session.getAttribute("cart");

        if (user == null || cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("login.jsp?redirect=confirmOrder.jsp");
            return;
        }

        // Collect the shipping and payment information
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String payment = request.getParameter("payment");

        try (Connection con = DBUtils.myconnect()) {
            con.setAutoCommit(false); // Begin transaction

            // **Step 1: Insert into 'order' table**
            String insertOrderSQL = "INSERT INTO `ordertable` (restaurantid, userid, orderDate, totalamount, status, paymentmode) VALUES (?, ?, NOW(), ?, 'Pending', ?)";
            PreparedStatement orderStmt = con.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            int restaurantId = cart.getRestaurantId(); // Retrieve restaurant ID from cart
            orderStmt.setInt(1, restaurantId);
            orderStmt.setInt(2, user.getUserId());
            orderStmt.setDouble(3, cart.getTotalAmount());
            orderStmt.setString(4, payment);
            orderStmt.executeUpdate();

            // Retrieve generated order ID
            ResultSet orderRs = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (orderRs.next()) {
                orderId = orderRs.getInt(1);
            }

            // **Step 2: Insert into 'orderitem' table**
            String insertOrderItemSQL = "INSERT INTO orderitem (orderid, menuid, quantity, itemtotal) VALUES (?, ?, ?, ?)";
            PreparedStatement orderItemStmt = con.prepareStatement(insertOrderItemSQL);

            for (CartItem item : cart.getItems().values()) {
                orderItemStmt.setInt(1, orderId);
                orderItemStmt.setInt(2, item.getItemId());
                orderItemStmt.setInt(3, item.getQuantity());
                orderItemStmt.setDouble(4, item.getSubtotal());
                orderItemStmt.addBatch();
            }
            orderItemStmt.executeBatch();

            // **Step 3: Insert into 'orderhistory' table**
            String insertOrderHistorySQL = "INSERT INTO orderhistory (orderid, userid, orderdate, totalamount, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement orderHistoryStmt = con.prepareStatement(insertOrderHistorySQL);
            orderHistoryStmt.setInt(1, orderId);
            orderHistoryStmt.setInt(2, user.getUserId());
            orderHistoryStmt.setDate(3,new java.sql.Date(System.currentTimeMillis()));
            orderHistoryStmt.setDouble(4, cart.getTotalAmount());
            orderHistoryStmt.setString(5, "Order Placed"); // Set initial status
            orderHistoryStmt.executeUpdate();

            // Commit the transaction
            con.commit();

            // Clear the cart after the order is confirmed
            session.removeAttribute("cart");

            // Redirect to order success page
            response.sendRedirect("orderSuccess.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception and rollback transaction
            try (Connection con = DBUtils.myconnect()) {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            response.sendRedirect("orderFailure.jsp");
        }
    }
}
