<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Cart, com.tap.model.CartItem"%>
<%@ page import="java.util.Map"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" type="text/css" href="styles/cart.css">
</head>
<body>
    <h2>Your Cart</h2>

    <%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart != null && !cart.getItems().isEmpty()) {
    %>
    <div class="cart-container">
        <%
        for (Object obj : cart.getItems().entrySet()) {
            Map.Entry<Integer, CartItem> entry = (Map.Entry<Integer, CartItem>) obj;
            CartItem item = entry.getValue();
        %>
        <div class="cart-item">
            <p><strong>Item:</strong> <%=item.getName()%></p>
            <p><strong>Price:</strong> Rs.<%=item.getPrice()%></p>
            <p><strong>Quantity:</strong></p>
            <form action="cart" method="post" class="inline-form">
                <input type="hidden" name="action" value="update" /> 
                <input type="hidden" name="itemId" value="<%=entry.getKey()%>" /> 
                <input type="number" name="quantity" value="<%=item.getQuantity()%>" min="0" /> 
                <input type="submit" value="Update" class="btn update-btn" />
            </form>
            <p><strong>Subtotal:</strong> Rs.<%=item.getSubtotal()%></p>
            <form action="cart" method="post" class="inline-form">
                <input type="hidden" name="action" value="remove" /> 
                <input type="hidden" name="itemId" value="<%=entry.getKey()%>" /> 
                <input type="submit" value="Remove" class="btn remove-btn" />
            </form>
        </div>
        <%
        }
        %>
    </div>
    <br/>

    <div class="cart-actions">
        <form action="cart" method="post">
            <input type="hidden" name="action" value="clear" /> 
            <input type="submit" value="Clear Cart" class="btn clear-btn" />
        </form>
        <form action="checkout.jsp" method="post">
            <input type="submit" value="Proceed to Checkout" class="btn proceed-btn" />
        </form>
        <a href="menu.jsp" class="btn add-items-btn">Add more Items</a>
    </div>
    <%
    } else {
    %>
    <p>Your cart is empty.</p>
    <a href="menu.jsp" class="btn add-items-btn">Add more Items</a>
    <%
    }
    %>
</body>
</html>
