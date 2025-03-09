package com.bsc.megacitycab.dao;

import com.bsc.megacitycab.models.Booking;
import com.bsc.megacitycab.utils.DBConnection;

import jakarta.servlet.http.HttpSession;  // Make sure to import HttpSession

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDAO {

    private final Connection connection;

    // Constructor to initialize connection
    public BookingDAO(Connection connection) throws SQLException {
        // Initialize connection using DBConnection utility
        this.connection = DBConnection.getConnection();  // Assuming DBConnection is a utility class to get the connection
    }

    // Method to save the booking
    public void saveBooking(Booking booking) throws SQLException {
        // Retrieve the customerId directly from the booking object
        Integer customerId = booking.getCustomerId();

        if (customerId == null) {
            throw new SQLException("Customer ID is missing in the booking.");
        }

        // SQL query to insert booking details into the database
        String query = "INSERT INTO bookings (order_number, customer_id, customer_name, customer_phone, pickup_location_id, drop_location_id, vehicle_id, fare) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, booking.getOrderNumber());
            ps.setInt(2, booking.getCustomerId());  // Using the customer_id passed in the booking object
            ps.setString(3, booking.getCustomerName());
            ps.setString(4, booking.getCustomerPhone());
            ps.setInt(5, booking.getPickupLocationId());
            ps.setInt(6, booking.getDropLocationId());
            ps.setInt(7, booking.getVehicleId());
            ps.setDouble(8, booking.getFare());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking successfully saved.");
                System.out.println("Saving booking for customerId: " + booking.getCustomerId());
            } else {
                throw new SQLException("Failed to save the booking.");
            }
        }
    }


    // Optionally, you could have a method to close the connection, if needed
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
