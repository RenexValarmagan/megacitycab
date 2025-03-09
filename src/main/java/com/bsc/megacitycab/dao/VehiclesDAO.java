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
}
