package com.bsc.megacitycab.dao;

import com.bsc.megacitycab.models.Customer;
import com.bsc.megacitycab.utils.DBConnection;

import java.sql.*;

public class CustomerDAO {

    // Register a new customer
    public static boolean registerCustomer(String name, String address, String nic, String phone, String username, String password) {
        boolean success = false;

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("Database connection failed!");
                return false;
            }

            // Generate a unique customer registration number
            String regNumber = generateRegistrationNumber(conn);

            String sql = "INSERT INTO customers (customer_reg_number, name, address, nic, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, regNumber);
                stmt.setString(2, name);
                stmt.setString(3, address);
                stmt.setString(4, nic);
                stmt.setString(5, phone);
                stmt.setString(6, username);
                stmt.setString(7, password); // Password is stored in plain text (consider encrypting it in a real app)

                int affectedRows = stmt.executeUpdate();
                success = affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log exception
        }

        return success;
    }

    // Generate a unique registration number for a new customer
    private static String generateRegistrationNumber(Connection conn) throws SQLException {
        String prefix = "CUST";
        int count = 1;

        String sql = "SELECT COUNT(*) FROM customers";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count += rs.getInt(1);
            }
        }

        return prefix + String.format("%03d", count);
    }

    // Authenticate a customer by username and password
    public static Customer authenticateCustomer(String username, String password) {
        Customer customer = null;
        String query = "SELECT * FROM customers WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);  // Set username parameter
            stmt.setString(2, password);  // Set password parameter (hashed password in real applications)

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Create a Customer object from the result set
                customer = new Customer(
                        rs.getString("customer_reg_number"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("nic"),
                        rs.getString("username"),
                        rs.getString("password")  // Password is returned (store hashed version in real applications)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log exception
        }
        return customer;  // Return the customer object if found, else null
    }
}
