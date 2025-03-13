package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.VehiclesDAO;
import com.bsc.megacitycab.models.Vehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class VehicleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // Get the action parameter from the request

        // Perform operation based on action type
        switch (action) {
            case "add":
                addVehicle(request, response);
                break;
            case "edit":
                updateVehicle(request, response);
                break;
            case "delete":
                deleteVehicle(request, response);
                break;
            case null:
            default:
                response.sendRedirect("admin-dashboard.jsp?error=Invalid action");
        }
    }


    // Add a new vehicle
    private void addVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get vehicle details from the request (type and status)
        String type = request.getParameter("type");
        String status = request.getParameter("status");

        // Create a new Vehicle object with the details, setting ID to 0 (since ID is auto-generated in DB)
        Vehicle newVehicle = new Vehicle(0, type, status);  // ID = 0, as it's auto-generated

        // Instantiate the DAO class to handle database operations
        VehiclesDAO vehiclesDAO = new VehiclesDAO();

        // Call the addVehicle method from DAO, passing the new vehicle object
        boolean result = vehiclesDAO.addVehicle(newVehicle);

        // Redirect to the dashboard with a success or failure message
        if (result) {
            response.sendRedirect("admin-dashboard.jsp?success=Vehicle added");
        } else {
            response.sendRedirect("admin-dashboard.jsp?error=Failed to add vehicle");
        }
    }



    // Edit an existing vehicle
    protected void updateVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vehicleIdParam = request.getParameter("vehicleId");

        // Check if vehicleId is null or empty
        if (vehicleIdParam == null || vehicleIdParam.trim().isEmpty()) {
            response.sendRedirect("error.jsp?message=Vehicle ID is missing.");
            return;
        }

        int vehicleId = 0;
        try {
            vehicleId = Integer.parseInt(vehicleIdParam); // Parse the vehicleId to an integer
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?message=Invalid Vehicle ID format.");
            return;
        }

        // Get other vehicle details
        String type = request.getParameter("type");
        String status = request.getParameter("status");

        // Create a Vehicle object with the provided details
        Vehicle vehicle = new Vehicle(vehicleId, type, status);

        // Call DAO to update the vehicle
        boolean result = VehiclesDAO.updateVehicle(vehicle);

        // Redirect based on the result
        if (result) {
            response.sendRedirect("admin-dashboard.jsp?success=Vehicle updated successfully.");
        } else {
            response.sendRedirect("admin-dashboard.jsp?error=Failed to update vehicle.");
        }
    }



    // Delete a vehicle
    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicle_id"));

        VehiclesDAO vehiclesDAO = new VehiclesDAO();
        boolean result = vehiclesDAO.deleteVehicle(vehicleId);

        if (result) {
            response.sendRedirect("admin-dashboard.jsp?success=Vehicle deleted");
        } else {
            response.sendRedirect("admin-dashboard.jsp?error=Failed to delete vehicle");
        }
    }
}
