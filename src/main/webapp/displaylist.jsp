<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.tap.model.OrderItem"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Items</title>
    <link rel="stylesheet" type="text/css" href="styles/displaylist.css">
</head>
<body>
    <div class="container">
        <h1>Order Items</h1>

        <%
        ArrayList<OrderItem> orderItemList = (ArrayList<OrderItem>) session.getAttribute("orderItemList");

        if (orderItemList != null && !orderItemList.isEmpty()) {
        %>
        <div class="order-item-list">
            <%
            for (OrderItem orderItem : orderItemList) {
            %>
            <div class="order-item">
                <p><strong>Menu ID:</strong> <%= orderItem.getMenuid() %></p>
                <p><strong>Quantity:</strong> <%= orderItem.getQuantity() %></p>
                <p><strong>Item Total:</strong> <%= orderItem.getSubtotal() %></p>
                <hr>
            </div>
            <%
            }
            %>
        </div>
        <%
        } else {
        %>
        <p>No items found for this order.</p>
        <%
        }
        %>

        <a href="orderHistory" class="back-link">Back to Order History</a>
    </div>
</body>
</html>
