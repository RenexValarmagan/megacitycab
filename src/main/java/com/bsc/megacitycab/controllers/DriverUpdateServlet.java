package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.DriverDAO;
import com.bsc.megacitycab.models.Driver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DriverUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the parameters from the form
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");

        // Create a Driver object with the provided details
        Driver driver = new Driver(driverId, name, phone, 0, status); // Assuming vehicle_id is optional and 0 means no vehicle

        // Call the updateDriver method with the Driver object
        boolean success = DriverDAO.updateDriver(driver);

        if (success) {
            response.sendRedirect("admin-dashboard.jsp?message=Driver updated successfully.");
        } else {
            response.sendRedirect("admin-dashboard.jsp?error=Failed to update driver.");
        }
    }
}
