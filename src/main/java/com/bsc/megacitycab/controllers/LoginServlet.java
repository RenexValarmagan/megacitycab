package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.UserDAO;
import com.bsc.megacitycab.dao.CustomerDAO;
import com.bsc.megacitycab.models.User;
import com.bsc.megacitycab.models.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authenticate user (admin) first
        User user = UserDAO.authenticateUser(username, password);

        if (user != null) {
            // Admin login successful
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());  // Store user_id in session
            session.setAttribute("role", user.getRole());

            // Redirect to admin dashboard
            response.sendRedirect("admin-dashboard.jsp");
        } else {
            // User not found, now check for customer login
            Customer customer = CustomerDAO.authenticateCustomer(username, password);

            if (customer != null) {
                // Customer login successful
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);  // Store customer in session
                session.setAttribute("customerRegNumber", customer.getCustomerRegNumber());  // Store customer_reg_number in session
                session.setAttribute("role", "customer");

                // Redirect to customer dashboard
                response.sendRedirect("customer-dashboard.jsp");
            } else {
                // Both user and customer not found
                response.sendRedirect("index.jsp?error=true");
            }
        }
    }
}
