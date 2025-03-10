package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.BookingDAO;
import com.bsc.megacitycab.models.Booking;
import com.bsc.megacitycab.utils.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;

public class BookingServlet extends HttpServlet {

    private BookingDAO bookingDAO;

    @Override
    public void init() {
        // Initialize the DAO with the connection
        bookingDAO = new BookingDAO(DBConnection.getConnection());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve customer_id and name from session
            HttpSession session = request.getSession();
            Integer customerId = (Integer) session.getAttribute("customerId");
            String customerName = (String) session.getAttribute("customerName");

            if (customerId == null || customerName == null) {
                response.sendRedirect("index.jsp");
                return;
            }

            // Extract form parameters and handle missing parameters
            String customerPhone = request.getParameter("customerPhone");
            if (customerPhone == null || customerPhone.isEmpty()) {
                request.setAttribute("error", "Phone number is required.");
                request.getRequestDispatcher("bookingForm.jsp").forward(request, response);
                return;
            }

            int pickupLocationId = parseInt(request.getParameter("pickupLocationId"));
            int dropLocationId = parseInt(request.getParameter("dropLocationId"));
            int vehicleId = parseInt(request.getParameter("vehicleId"));

            // Validate the IDs (they should be positive integers)
            if (pickupLocationId <= 0 || dropLocationId <= 0 || vehicleId <= 0) {
                request.setAttribute("error", "Invalid location or vehicle selection.");
                request.getRequestDispatcher("bookingForm.jsp").forward(request, response);
                return;
            }

            double fare = calculateFare(pickupLocationId, dropLocationId, vehicleId); // Calculate fare
            // Retrieve an available driver (if any)
            int driverId = bookingDAO.getAvailableDriverId();

            // Create a new Booking object with the assigned driverId (or null if no driver available)
            Booking booking = new Booking(generateOrderNumber(), customerId, customerName, customerPhone, pickupLocationId, dropLocationId, vehicleId, (driverId != -1) ? driverId : null, fare);

            // Save the booking in the database
            bookingDAO.saveBooking(booking);

            // Check if the driver was assigned after the save operation
            if (booking.getDriverId() == null) {
                request.setAttribute("error", "No available driver for this booking.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // Set booking details as request attributes
            request.setAttribute("orderNumber", booking.getOrderNumber());
            request.setAttribute("customerName", booking.getCustomerName());
            request.setAttribute("customerPhone", booking.getCustomerPhone());
            request.setAttribute("pickupLocation", booking.getPickupLocationId());
            request.setAttribute("dropLocation", booking.getDropLocationId());
            request.setAttribute("vehicleId", booking.getVehicleId());
            request.setAttribute("fare", booking.getFare());

            // Forward to bookingConfirmation.jsp
            request.getRequestDispatcher("bookingConfirmation.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error processing booking: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }



    // Example method to calculate fare (implement actual logic)
    private double calculateFare(int pickupLocationId, int dropLocationId, int vehicleId) {
        // Replace with your actual fare calculation logic
        return 100.0;  // Sample fixed fare for now
    }

    // Method to safely parse integers from request parameters
    private int parseInt(String param) {
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return -1; // Invalid value
        }
    }

    // Generate a unique order number (simplified version)
    private String generateOrderNumber() {
        // Implement a method to generate a unique order number (e.g., UUID)
        return "ORD" + System.currentTimeMillis(); // Example order number based on timestamp
    }

    @Override
    public void destroy() {
        // Close the database connection when the servlet is destroyed
        try {
            if (bookingDAO != null) {
                bookingDAO.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
