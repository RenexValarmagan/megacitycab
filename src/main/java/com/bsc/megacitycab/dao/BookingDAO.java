package com.bsc.megacitycab.dao;

import com.bsc.megacitycab.models.Booking;
import com.bsc.megacitycab.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDAO {
    public static void saveBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO bookings (order_number, name, address, phone, destination, fare) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, booking.getOrderNumber());
            ps.setString(2, booking.getName());
            ps.setString(3, booking.getAddress());
            ps.setString(4, booking.getPhone());
            ps.setString(5, booking.getDestination());
            ps.setDouble(6, booking.getFare());

            ps.executeUpdate();
        }
    }
}
