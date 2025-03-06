package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.BookingDAO;
import com.bsc.megacitycab.models.Booking;
import com.bsc.megacitycab.models.User;
import com.bsc.megacitycab.utils.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

import java.util.UUID;

public class BookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");
        Integer userId = (Integer) session.getAttribute("userId");  // Retrieve customer_id from session

        if (loggedInUser == null || userId == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Get the form values
        int pickupId = Integer.parseInt(request.getParameter("pickupLocation"));
        int dropId = Integer.parseInt(request.getParameter("dropLocation"));
        String vehicleType = request.getParameter("vehicleType");
        String customerAddress = request.getParameter("address");  // Capture customer address
        String customerPhoneNumber = request.getParameter("phoneNumber");  // Capture customer phone number

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBConnection.getConnection();

            // Generate unique order number
            String orderNumber = "ORD" + UUID.randomUUID().toString().substring(0, 6);

            // Get distance and calculate fare
            double fare = calculateFare(con, pickupId, dropId);

            // Assign an available vehicle
            int vehicleId = assignVehicle(con, vehicleType);
            if (vehicleId == -1) {
                response.sendRedirect("booking.jsp?error=No available vehicles");
                return;
            }

            // Assign a driver for the vehicle
            int driverId = assignDriver(con, vehicleId);
            if (driverId == -1) {
                response.sendRedirect("booking.jsp?error=No available drivers");
                return;
            }

            // Create a Booking object
            Booking booking = new Booking(orderNumber, userId, customerAddress, customerPhoneNumber, pickupId, dropId, vehicleId, driverId, fare);

            // Save booking to the database
            BookingDAO.saveBooking(booking);

            // Redirect to booking confirmation page
            response.sendRedirect("bookingConfirmation.jsp?order=" + orderNumber);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("booking.jsp?error=true");
        } finally {
            try { if (pstmt != null) pstmt.close(); if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private double calculateFare(Connection con, int pickupId, int dropId) throws SQLException {
        String sql = "SELECT distance FROM distances WHERE (pickup_id = ? AND drop_id = ?) OR (pickup_id = ? AND drop_id = ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, pickupId);
        pstmt.setInt(2, dropId);
        pstmt.setInt(3, dropId);
        pstmt.setInt(4, pickupId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            double distance = rs.getDouble("distance");
            return (distance <= 1) ? 100 : 120 * distance;
        }
        return 0;
    }

    private int assignVehicle(Connection con, String vehicleType) throws SQLException {
        String sql = "SELECT id FROM vehicles WHERE type = ? AND status = 'available' LIMIT 1";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, vehicleType);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int vehicleId = rs.getInt("id");

            // Mark vehicle as booked
            String updateSql = "UPDATE vehicles SET status = 'booked' WHERE id = ?";
            PreparedStatement updatePstmt = con.prepareStatement(updateSql);
            updatePstmt.setInt(1, vehicleId);
            updatePstmt.executeUpdate();

            return vehicleId;
        }
        return -1;
    }

    private int assignDriver(Connection con, int vehicleId) throws SQLException {
        String sql = "SELECT id FROM drivers WHERE vehicle_id = ? AND status = 'available' LIMIT 1";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, vehicleId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int driverId = rs.getInt("id");

            // Mark driver as booked
            String updateSql = "UPDATE drivers SET status = 'booked' WHERE id = ?";
            PreparedStatement updatePstmt = con.prepareStatement(updateSql);
            updatePstmt.setInt(1, driverId);
            updatePstmt.executeUpdate();

            return driverId;
        }
        return -1;
    }
}
