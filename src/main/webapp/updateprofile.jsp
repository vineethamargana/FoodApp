<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <link rel="stylesheet" type="text/css" href="styles/updateprofile.css">
</head>
<body>
    <header>
        <h1>Update Profile</h1>
        <nav>
            <a href="home">Home</a>
            <a href="cart">View Cart</a>
            <a href="orderHistory">View Order History</a>
            <a href="logout">Log Out</a>
        </nav>
    </header>

    <div class="container">
        <h2>Update Your Profile</h2>
        <form action="updateProfile" method="post">
            <input type="hidden" name="action" value="update">

            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="<%= ((User) session.getAttribute("loggedInUser")).getUsername() %>" required>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="<%= ((User) session.getAttribute("loggedInUser")).getAddress() %>" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="text" id="phone" name="phone" value="<%= ((User) session.getAttribute("loggedInUser")).getPhonenumber() %>" required>
            </div>

            <input type="submit" value="Update">
        </form>
    </div>
</body>
</html>
