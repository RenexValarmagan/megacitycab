package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.DriverDAO;
import com.bsc.megacitycab.models.Driver;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class DriverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Perform operation based on action type
        switch (action) {
            case "add":
                addDriver(request, response);
                break;
            case "edit":
                editDriver(request, response);
                break;
            case "delete":
                deleteDriver(request, response);
                break;
            case null:
            default:
                response.sendRedirect("admin-dashboard.jsp?error=Invalid action");
        }
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));
        String status = request.getParameter("status");

        Driver newDriver = new Driver(0, name, phone, vehicleId, status); // ID is 0 for a new driver

        DriverDAO driverDAO = new DriverDAO();
        boolean result = driverDAO.addDriver(newDriver);

        if (result) {
            response.sendRedirect("admin-dashboard.jsp?success=Driver added");
        } else {
            response.sendRedirect("admin-dashboard.jsp?error=Failed to add driver");
        }
    }

    protected void editDriver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Log request parameters for debugging
            System.out.println("Received request parameters: " + request.getParameterMap());

            // Get driver ID safely
            String driverIdParam = request.getParameter("driverId");
            if (driverIdParam == null || driverIdParam.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Driver ID is required.");
                return;
            }
            int driverId = Integer.parseInt(driverIdParam);

            // Proceed with update logic
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String status = request.getParameter("status");

            String vehicleIdParam = request.getParameter("vehicle_id");
            Integer vehicleId = (vehicleIdParam == null || vehicleIdParam.trim().isEmpty()) ? null : Integer.parseInt(vehicleIdParam);

            Driver driver = new Driver(driverId, name, phone, vehicleId, status);
            boolean success = DriverDAO.updateDriver(driver);

            if (success) {
                response.sendRedirect("admin-dashboard.jsp?success=true");
            } else {
                response.sendRedirect("admin-dashboard.jsp?error=true");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format in request parameters.");
            e.printStackTrace();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating driver details.");
            e.printStackTrace();
        }
    }



    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        DriverDAO driverDAO = new DriverDAO();
        boolean result = driverDAO.deleteDriver(driverId);

        if (result) {
            response.sendRedirect("admin-dashboard.jsp?success=Driver deleted");
        } else {
            response.sendRedirect("admin-dashboard.jsp?error=Failed to delete driver");
        }
    }
}