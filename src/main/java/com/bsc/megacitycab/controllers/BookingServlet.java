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
import java.sql.SQLException;

public class BookingServlet extends HttpServlet {

    private BookingDAO bookingDAO;

    @Override
    public void init() {
        // Initialize the DAO with the connection
        try {
            bookingDAO = new BookingDAO(DBConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve customer_id and name from session
            HttpSession session = request.getSession();
            Integer customerId = (Integer) session.getAttribute("customerId");
            String customerName = (String) session.getAttribute("customerName");

            System.out.println("Customer ID: " + customerId);
            System.out.println("Customer Name: " + customerName);

            if (customerId == null || customerName == null) {
                response.sendRedirect("index.jsp");
                return;
            }

            // Extract form parameters
            String orderNumber = generateOrderNumber(); // Auto-generate the order number
            String customerPhone = request.getParameter("customerPhone");
            int pickupLocationId = Integer.parseInt(request.getParameter("pickupLocationId"));
            int dropLocationId = Integer.parseInt(request.getParameter("dropLocationId"));
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            double fare = calculateFare(pickupLocationId, dropLocationId, vehicleId); // Calculate fare based on the selected options

            // Create a new Booking object
            Booking booking = new Booking(orderNumber, customerId, customerName, customerPhone, pickupLocationId, dropLocationId, vehicleId, fare);

            // Save the booking in the database
            bookingDAO.saveBooking(booking);

            // Redirect to a success page
            response.sendRedirect("success.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    // Example method to calculate fare
    private double calculateFare(int pickupLocationId, int dropLocationId, int vehicleId) {
        // Implement fare calculation based on the selected locations and vehicle type
        // For now, return a fixed fare as an example
        return 100.0;  // Replace this with your actual fare calculation logic
    }

    // Generate a unique order number (simplified version)
    private String generateOrderNumber() {
        // Implement a method to generate a unique order number (for example, using timestamp or UUID)
        return "ORD" + System.currentTimeMillis(); // Example order number based on timestamp
    }
}
