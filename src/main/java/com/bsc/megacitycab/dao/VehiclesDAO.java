package com.bsc.megacitycab.dao;

import com.bsc.megacitycab.utils.DBConnection;
import com.bsc.megacitycab.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiclesDAO {

    public static Vehicle getVehicleById(int vehicleId) {
        Vehicle vehicle = null;
        String query = "SELECT id, type, status FROM vehicles WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, vehicleId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle(
                            rs.getInt("id"),
                            rs.getString("type"),
                            rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }


    // Fetch all vehicles with ID, type, and status
    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT id, type, status FROM vehicles";  // Fetch 'status' field too

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String status = rs.getString("status");  // Fetch 'status' from database
                vehicles.add(new Vehicle(id, type, status));  // Pass 'status' to the constructor
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Update vehicle type and status by vehicle ID
    public static boolean updateVehicle(Vehicle vehicle) {
        String query = "UPDATE vehicles SET type = ?, status = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, vehicle.getType());
            ps.setString(2, vehicle.getStatus());
            ps.setInt(3, vehicle.getId());

            // Execute the update and check if any rows were affected
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    // Delete vehicle by ID
    public static boolean deleteVehicle(int vehicleId) {
        String query = "DELETE FROM vehicles WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, vehicleId);
            int deletedRows = ps.executeUpdate();
            return deletedRows > 0;  // Returns true if delete was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addVehicle(Vehicle newVehicle) {
        String query = "INSERT INTO vehicles (type, status) VALUES (?, ?)";  // Correct the query for insertion
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            ps.setString(1, newVehicle.getType());
            ps.setString(2, newVehicle.getStatus());

            // Execute the update
            int insertedRows = ps.executeUpdate();

            // If insertion was successful, return true
            return insertedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();  // Log any errors
            return false;  // Return false if there is an error
        }
    }



}
