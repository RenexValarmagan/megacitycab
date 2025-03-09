package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.BookingDAO;
import com.bsc.megacitycab.models.Booking;
import com.bsc.megacitycab.utils.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            // Extract form parameters
            String orderNumber = request.getParameter("orderNumber");
            String customerName = request.getParameter("customerName"); // Auto-filled
            String customerPhone = request.getParameter("customerPhone");
            int pickupLocationId = Integer.parseInt(request.getParameter("pickupLocationId"));
            int dropLocationId = Integer.parseInt(request.getParameter("dropLocationId"));
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            double fare = calculateFare(pickupLocationId, dropLocationId, vehicleId); // Assuming fare calculation method

            // Create a new Booking object
            Booking booking = new Booking(orderNumber, customerName, customerPhone, pickupLocationId, dropLocationId, vehicleId, fare);

            // Save booking in the database
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
        return 100.0;  // Example fare (replace with actual calculation logic)
    }
}
