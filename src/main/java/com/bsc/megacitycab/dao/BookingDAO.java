package com.bsc.megacitycab.dao;

import com.bsc.megacitycab.models.Booking;
import com.bsc.megacitycab.utils.DBConnection;
import java.sql.*;

public class BookingDAO {

    // Updated saveBooking method to accept a Booking object and store data in the database
    public static void saveBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (order_number, customer_id, customer_address, customer_phone_number, pickup_location_id, drop_location_id, vehicle_id, driver_id, fare) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, booking.getOrderNumber());
            pstmt.setInt(2, booking.getCustomerId());
            pstmt.setString(3, booking.getCustomerAddress());  // Adding customer address
            pstmt.setString(4, booking.getCustomerPhoneNumber()); // Adding customer phone number
            pstmt.setInt(5, booking.getPickupLocationId());
            pstmt.setInt(6, booking.getDropLocationId());
            pstmt.setInt(7, booking.getVehicleId());
            pstmt.setInt(8, booking.getDriverId());
            pstmt.setDouble(9, booking.getFare());
            pstmt.executeUpdate();
        }
    }

    // Fare calculation remains unchanged
    public static double calculateFare(int pickupId, int dropId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String sql = "SELECT distance FROM distances WHERE (pickup_id = ? AND drop_id = ?) OR (pickup_id = ? AND drop_id = ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, pickupId);
            pstmt.setInt(2, dropId);
            pstmt.setInt(3, dropId);
            pstmt.setInt(4, pickupId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                double distance = rs.getDouble("distance");
                return (distance <= 1) ? 100 : 120 * distance;
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
        return 0;
    }

    // Vehicle assignment remains unchanged
    public static int assignVehicle(String vehicleType) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String sql = "SELECT id FROM vehicles WHERE type = ? AND status = 'available' LIMIT 1";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, vehicleType);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int vehicleId = rs.getInt("id");

                // Mark vehicle as booked
                String updateSql = "UPDATE vehicles SET status = 'booked' WHERE id = ?";
                PreparedStatement updatePstmt = con.prepareStatement(updateSql);
                updatePstmt.setInt(1, vehicleId);
                updatePstmt.executeUpdate();
                updatePstmt.close();

                return vehicleId;
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
        return -1;
    }

    // Driver assignment remains unchanged
    public static int assignDriver(int vehicleId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String sql = "SELECT id FROM drivers WHERE vehicle_id = ? AND status = 'available' LIMIT 1";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, vehicleId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int driverId = rs.getInt("id");

                // Mark driver as assigned
                String updateSql = "UPDATE drivers SET status = 'assigned' WHERE id = ?";
                PreparedStatement updatePstmt = con.prepareStatement(updateSql);
                updatePstmt.setInt(1, driverId);
                updatePstmt.executeUpdate();
                updatePstmt.close();

                return driverId;
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
        return -1;
    }
}
