package com.bsc.megacitycab.dao;

import com.bsc.megacitycab.models.Booking;
import com.bsc.megacitycab.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDAO {

    private Connection connection;

    // Constructor to initialize connection
    public BookingDAO(Connection connection) throws SQLException {
        // Initialize connection using DBConnection utility
        this.connection = DBConnection.getConnection();  // Assuming DBConnection is a utility class to get the connection
    }

    // Method to save the booking
    public void saveBooking(Booking booking) throws SQLException {
        // Step 1: We now use customerId directly from the Booking object.
        int customerId = booking.getCustomerId();  // We already have the customer_id directly

        // Step 2: Insert the booking using customer_id
        String query = "INSERT INTO bookings (order_number, customer_id, customer_name, customer_phone, pickup_location_id, drop_location_id, vehicle_id, fare) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, booking.getOrderNumber());
            ps.setInt(2, customerId);  // Use the customer_id obtained from the Booking object
            ps.setString(3, booking.getCustomerName());
            ps.setString(4, booking.getCustomerPhone());
            ps.setInt(5, booking.getPickupLocationId());
            ps.setInt(6, booking.getDropLocationId());
            ps.setInt(7, booking.getVehicleId());
            ps.setDouble(8, booking.getFare());

            int rowsAffected = ps.executeUpdate();  // Execute the insert query
            if (rowsAffected > 0) {
                System.out.println("Booking successfully saved.");
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
