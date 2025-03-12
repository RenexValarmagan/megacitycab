package com.bsc.megacitycab.controllers;

import com.bsc.megacitycab.dao.BookingDAO;
import com.bsc.megacitycab.utils.DBConnection;
import com.bsc.megacitycab.models.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;

        if (role == null || !"admin".equals(role)) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        // Fetch all bookings
        try (Connection connection = DBConnection.getConnection()) {
            BookingDAO bookingDAO = new BookingDAO(connection);
            List<Booking> bookings = bookingDAO.getAllBookings();

            // Debugging: Check if bookings are fetched
            System.out.println("Fetched bookings: " + bookings.size()); // Log size of fetched bookings

            // If bookings are empty, print a debug message
            if (bookings.isEmpty()) {
                System.out.println("No bookings found.");
            }

            // Set the bookings as an attribute to pass it to the JSP
            request.setAttribute("bookings", bookings);

            // Forward the request to the admin dashboard JSP
            request.getRequestDispatcher("/admin-dashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Database error while fetching bookings.");
        }
    }
}
