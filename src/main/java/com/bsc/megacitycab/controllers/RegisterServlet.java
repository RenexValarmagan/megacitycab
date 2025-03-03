package com.bsc.megacitycab.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.bsc.megacitycab.dao.UserDAO;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Automatically set role to "customer"
        String role = "customer";

        boolean success = UserDAO.registerUser(username, password, role);

        if (success) {
            response.sendRedirect("register.jsp?success=true");
        } else {
            response.sendRedirect("register.jsp?error=true");
        }
    }
}
