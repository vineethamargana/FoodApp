<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.tap.model.User"%>
<%@ page import="com.tap.model.Restaurant"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Featured Restaurants</title>
    <link rel="stylesheet" type="text/css" href="styles/home.css">
</head>
<body>
    <header>
        <h1>Welcome to VS Online Food Delivery</h1>
        <nav class="main-nav">
            <div class="nav-left">
                <a href="home">Home</a>
            </div>
            <div class="nav-right">
                <%
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                if (loggedInUser != null) {
                %>
                <span>Welcome, <%= loggedInUser.getUsername() %>!</span>
                <select id="account-options" onchange="window.location.href=this.value;">
                    <option value="#">Select</option>
                    <option value="cart">View Cart</option>
                    <option value="orderHistory">View Order History</option>
                    <option value="updateprofile.jsp">Update Profile</option>
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
            </div>
        </nav>
    </header>

    <main>
        <h2>Featured Restaurants</h2>
        <section>
            <%
            ArrayList<Restaurant> restaurantList = (ArrayList<Restaurant>) session.getAttribute("restaurantList");
            if (restaurantList != null && !restaurantList.isEmpty()) {
            %>
            <div class="restaurant-list">
                <%
                for (Restaurant restaurant : restaurantList) {
                    String imagePath = restaurant.getImgpath() != null ? restaurant.getImgpath() : "default.jpg";
                %>
                <div class="restaurant-card">
                    <img src="images/restaurants/<%= imagePath %>"
                        alt="Image of <%= restaurant.getRestaurantname() %>"
                        class="restaurant-image">
                    <h3><%= restaurant.getRestaurantname() %></h3>
                    <p><%= restaurant.getCuisiontype() %> - <%= restaurant.getDeliverytime() %> mins</p>
                    <a href="menu?restaurantid=<%= restaurant.getRestaurantid() %>">View Menu</a>
                </div>
                <%
                }
                %>
            </div>
            <%
            } else {
            %>
            <p>No featured restaurants are currently available. Please <a href="home">visit home</a> to view the restaurants.</p>
            <%
            }
            %>
        </section>
    </main>
</body>
</html>
