package com.bsc.megacitycab.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.bsc.megacitycab.dao.CustomerDAO;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("customer_name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Directly store it as plaintext

        // Register customer in the database
        boolean customerSuccess = CustomerDAO.registerCustomer(name, address, nic, phone, username, password);

        if (customerSuccess) {
            response.sendRedirect("register.jsp?success=true");
        } else {
            response.sendRedirect("register.jsp?error=true");
        }
    }
}
