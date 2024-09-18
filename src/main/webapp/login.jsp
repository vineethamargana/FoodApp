<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="styles/login.css">
</head>
<body>
  <center>
    <h1>Login Page</h1>
    <table>
      <form action="login" method="post"> <!-- calling login servlet -->
         <tr>
              <td>Email:</td>
              <td><input type="text" name="email"></td>
         </tr>
         <tr>
              <td>Password:</td>
              <td><input type="password" name="password"></td>
         </tr>
         <tr>
              <td></td>
              <td><input type="submit" value="Login"></td>
         </tr>
      </form>
      <% if (request.getAttribute("errorMessage") != null) { %>
         <p style="color:#EBD3F8;"><%= request.getAttribute("errorMessage") %></p> <!-- Fixed errorMessage -->
      <% } %>
    </table>
   </center>
</body>
</html>
