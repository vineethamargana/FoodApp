package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.dao.UserDAO;
import com.tap.daoimpl.UserDAOImpl;
import com.tap.model.User;

@WebServlet("/updateProfile")
public class UserProfile extends HttpServlet {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            HttpSession session = request.getSession();
            User loggedInUser = (User) session.getAttribute("loggedInUser");

            if (loggedInUser != null) {
                String username = request.getParameter("username");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");

                // Update the user details
                loggedInUser.setUsername(username);
                loggedInUser.setAddress(address);
                loggedInUser.setPhonenumber(phone);

                // Save the updated user details to the database
                userDAO.updateUser(loggedInUser);

                // Update the session with the new user details
                session.setAttribute("loggedInUser", loggedInUser);

                // Redirect to the home page or a profile page
                response.sendRedirect("home");
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }
}
