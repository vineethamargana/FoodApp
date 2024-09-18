<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.User" %>
<%@ page import="com.tap.model.Menu"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="styles/menu.css">
</head>
<body>
    <header>
        <h1>Restaurant Menu</h1>
        <nav>
            <a href="home">Home</a>
            <%
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
            %>
            <label for="userActions">User Options:</label>
            <select id="userActions" onchange="location = this.value;">
                <option value="#">-- Select an option --</option>
                <option value="cart">View Cart</option>
                <option value="orderHistory">View Order History</option>
                <option value="updateUser">Update Profile</option>
                <option value="logout">Log Out</option>
            </select>
            <%
            } else {
            %>
            <a href="login.jsp">Login</a> | 
            <a href="signup.jsp">Register</a>
            <%
            }
            %>
        </nav>
    </header>

    <h2>Menu Items</h2>
    <section class="menu-list">
        <%
        ArrayList<Menu> menuList = (ArrayList<Menu>) session.getAttribute("menuList");
        if (menuList != null && !menuList.isEmpty()) {
        %>
        <div class="menu-items">
            <%
            for (Menu menu : menuList) {
            %>
            <div class="menu-card">
                <img src="images/menu/<%= menu.getImgpath() %>" alt="Image of <%= menu.getItemName() %>" class="menu-image">
                <h3><%= menu.getItemName() %></h3>
                <p>Description: <%= menu.getDescription() %></p>
                <p>Price: Rs.<%= menu.getPrice() %></p>
                <p><%= menu.isAvailable() ? "Available" : "Not Available" %></p>

                <form action="cart" method="post">
                    <input type="hidden" name="menuid" value="<%= menu.getMenuid() %>">
                    <label for="quantity<%= menu.getMenuid() %>">Quantity:</label>
                    <input type="number" id="quantity<%= menu.getMenuid() %>" name="quantity" value="1" min="1">
                    <input type="submit" class="add-to-cart-btn" value="Add to Cart">
                    <input type="hidden" name="action" value="add">
                </form>
            </div>
            <%
            }
            %>
        </div>
        <%
        } else {
        %>
        <p>No menu items available.</p>
        <%
        }
        %>
    </section>
</body>
</html>
