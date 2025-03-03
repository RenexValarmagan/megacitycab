package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.UserDAO;
import com.bsc.megacitycab.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authenticate user
        User user = UserDAO.authenticateUser(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            // Redirect based on role
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin-dashboard.jsp");
            } else if ("customer".equals(user.getRole())) {
                response.sendRedirect("customer-dashboard.jsp");
            } else {
                response.sendRedirect("index.jsp?error=invalid_role");
            }
        } else {
            response.sendRedirect("index.jsp?error=true");
        }
    }
}
