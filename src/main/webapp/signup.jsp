<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign UP Form</title>
<link rel="stylesheet" type="text/css" href="styles/signup.css">
</head>
<body>
  <center>
    <h1>Register Here</h1>
    <form action="signup" method="post">
        <table>
         <tr>
             <td>Name:</td>
             <td><input type="text" name="name"></td>
         </tr>
         <tr>
             <td>Email ID:</td>
             <td><input type="email" name="email"></td>
         </tr>
         <tr>
             <td>Phone Number:</td>
             <td><input type="tel" name="phoneNumber"></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
         </tr>
         <tr>
            <td>Confirm Password:</td>
            <td><input type="password" name="cpassword"></td>
         </tr>
         <tr>
            <td>Address:</td>
            <td><input type="text" name="address"></td>
         </tr>
         <tr>
           <td></td>
           <td><input type="submit" value="Register me"></td>
         </tr>
        </table>
    </form>
  </center>
</body>
</html>
