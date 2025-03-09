package com.bsc.megacitycab.dao;

import com.bsc.megacitycab.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bsc.megacitycab.models.Location;  // Import Location model

public class LocationsDAO {

    // Fetch location ID based on name
    public static int getLocationId(String locationName) {
        int locationId = -1;
        String query = "SELECT id FROM locations WHERE name = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, locationName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                locationId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locationId;
    }

    // Fetch all locations (ID & Name)
    public static List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        String query = "SELECT id, name FROM locations";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                locations.add(new Location(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
}
