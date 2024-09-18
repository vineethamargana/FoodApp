<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome Page</title>
    <link rel="stylesheet" type="text/css" href="styles/index.css">
</head>
<body>
    <header>
        <h1>Welcome to VS</h1>
    </header>
    
    <nav> 
        <button onclick="location.href='signup.jsp'">Sign Up</button>
        <button onclick="location.href='login.jsp'">Login</button>
    </nav>
    
    <main>
        <img src="images/tapvineethahomeimg.jpg" height="300" width="300">
        <section>
            <h2>Get Started</h2>
            <button onclick="location.href='home.jsp'">Start Ordering</button>
        </section>
    </main>
    
    <footer>
        <p>&copy; 2024 <i>VS Food Delivery App</i>. All rights reserved.</p>
    </footer>
</body>
</html>
