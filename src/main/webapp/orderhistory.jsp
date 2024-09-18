<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="com.tap.model.OrderHistory"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <link rel="stylesheet" type="text/css" href="styles/orderhistory.css">
</head>
<body>
    <header>
        <h1>Your Order History</h1>
        <nav>
            <a href="home">Home</a>
            <a href="cart">View Cart</a>
            <a href="orderHistory">View Order History</a>
            <a href="logout">Log Out</a>
        </nav>
    </header>

    <div class="container">
        <%
        ArrayList<OrderHistory> orderHistoryList = (ArrayList<OrderHistory>) session.getAttribute("orderHistoryList");

        if (orderHistoryList != null && !orderHistoryList.isEmpty()) {
        %>
        <div class="order-history-list">
            <%
            for (OrderHistory orderHistory : orderHistoryList) {
            %>
            <div class="order-history-item">
                <p><strong>Order Date:</strong> <%= orderHistory.getOrderdate() %></p>
                <p><strong>Total Amount:</strong> <%= orderHistory.getTotalamount() %></p>
                <p><strong>Status:</strong> <%= orderHistory.getStatus() %></p>
                <form action="OrderItemServlet" method="get">
                    <input type="hidden" name="orderid" value="<%= orderHistory.getOrderid() %>" />
                    <button type="submit">View Order #<%= orderHistory.getOrderid() %></button>
                </form>
            </div>
            <hr>
            <%
            }
            %>
        </div>
        <%
        } else {
        %>
        <p>You have no order history.</p>
        <%
        }
        %>
    </div>

    <footer>
        <a href="home">Back to Home</a>
    </footer>
</body>
</html>
