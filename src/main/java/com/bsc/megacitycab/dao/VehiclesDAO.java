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

    public static int getVehicleId(String vehicleType) {
        int vehicleId = -1;
        String query = "SELECT id FROM vehicles WHERE type = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, vehicleType);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                vehicleId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleId;
    }

    // ✅ New method to fetch vehicle IDs and names
    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT id, type FROM vehicles";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                vehicles.add(new Vehicle(id, type));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public boolean updateVehicle(int vehicleId, String type, String status) {
        String query = "UPDATE vehicles SET type = ?, status = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, type);
            ps.setString(2, status);
            ps.setInt(3, vehicleId);

            int updatedRows = ps.executeUpdate();
            return updatedRows > 0; // Returns true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVehicle(int vehicleId) {
        String query = "DELETE FROM vehicles WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, vehicleId);
            int deletedRows = ps.executeUpdate();
            return deletedRows > 0; // Returns true if delete was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
