package com.bsc.megacitycab.dao;

import com.bsc.megacitycab.models.Driver;
import com.bsc.megacitycab.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    // Fetch all drivers
    public static List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT id, name, phone, vehicle_id, status FROM drivers";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Driver driver = new Driver(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getInt("vehicle_id"),
                        rs.getString("status")
                );
                drivers.add(driver);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    // Update driver details
    public static boolean updateDriver(Driver driver) {
        String query = "UPDATE drivers SET name = ?, phone = ?, vehicle_id = ?, status = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, driver.getName());
            ps.setString(2, driver.getPhone());
            ps.setObject(3, driver.getVehicleId() == 0 ? null : driver.getVehicleId(), Types.INTEGER); // Allow NULL
            ps.setString(4, driver.getStatus());
            ps.setInt(5, driver.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
