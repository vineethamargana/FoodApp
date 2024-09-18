<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.User, com.tap.model.Cart, com.tap.model.CartItem"%>
<%@ page import="java.util.Map"%>

<%
    User user = (User) session.getAttribute("loggedInUser");
    if (user == null) {
        response.sendRedirect("login.jsp?redirect=confirmOrder.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <link rel="stylesheet" type="text/css" href="styles/confirmorder.css">
</head>
<body>
    <div class="container">
        <h2>Order Confirmation</h2>
        <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
            double total = 0.0;
        %>
        <div class="order-summary">
            <h3>Order Summary</h3>
            <%
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem item = entry.getValue();
                double itemSubtotal = item.getSubtotal();
                total += itemSubtotal;
            %>
            <div class="order-item">
                <p>
                    <strong>Item:</strong> <%= item.getName() %><br/>
                    <strong>Quantity:</strong> <%= item.getQuantity() %><br/>
                    <strong>Price:</strong> Rs.<%= item.getPrice() %><br/>
                    <strong>Subtotal:</strong> Rs.<%= itemSubtotal %>
                </p>
            </div>
            <hr/>
            <%
            }
            %>
            <h3>Total: Rs.<%= total %></h3>
        </div>
        
        <div class="shipping-info">
            <h3>Shipping Information</h3>
            <p><strong>Name:</strong> <%= request.getParameter("name") %></p>
            <p><strong>Address:</strong> <%= request.getParameter("address") %></p>
            <p><strong>Phone:</strong> <%= request.getParameter("phone") %></p>
            <p><strong>Payment Method:</strong> <%= request.getParameter("payment") %></p>
        </div>

        <form action="confirmOrder" method="post" class="confirmation-form">
            <input type="hidden" name="total" value="<%= total %>">
            <input type="hidden" name="name" value="<%= request.getParameter("name") %>">
            <input type="hidden" name="address" value="<%= request.getParameter("address") %>">
            <input type="hidden" name="phone" value="<%= request.getParameter("phone") %>">
            <input type="hidden" name="payment" value="<%= request.getParameter("payment") %>">
            <input type="submit" value="OK" class="submit-btn">
        </form>
        <%
        } else {
        %>
        <p>Your cart is empty.</p>
        <%
        }
        %>
    </div>
</body>
</html>
