package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.VehiclesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class VehicleUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the parameters from the form
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String type = request.getParameter("type");
        String status = request.getParameter("status");

        VehiclesDAO vehiclesDAO = new VehiclesDAO();

        // Perform the update operation
        boolean success = vehiclesDAO.updateVehicle(vehicleId, type, status);

        if (success) {
            response.sendRedirect("admin-dashboard.jsp?message=Vehicle updated successfully.");
        } else {
            response.sendRedirect("admin-dashboard.jsp?error=Failed to update vehicle.");
        }
    }
}
