package com.bsc.megacitycab.dao;

import com.bsc.megacitycab.models.Booking;
import com.bsc.megacitycab.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private Connection connection;  // Make this instance-level

    // Constructor using passed connection
    public BookingDAO(Connection connection) {
        this.connection = connection; // Initialize the instance variable
    }

    // Modify the saveBooking method to return the driverId
    public Integer saveBooking(Booking booking) throws SQLException {
        Integer customerId = booking.getCustomerId();
        if (customerId == null) {
            throw new SQLException("Customer ID is missing in the booking.");
        }

        int driverId = getAvailableDriverId(); // Get an available driver (if any)

        String query = "INSERT INTO bookings (order_number, customer_id, customer_name, customer_phone, pickup_location_id, drop_location_id, vehicle_id, driver_id, fare) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            connection.setAutoCommit(false); // Begin transaction

            ps.setString(1, booking.getOrderNumber());
            ps.setInt(2, customerId);
            ps.setString(3, booking.getCustomerName());
            ps.setString(4, booking.getCustomerPhone());
            ps.setInt(5, booking.getPickupLocationId());
            ps.setInt(6, booking.getDropLocationId());
            ps.setInt(7, booking.getVehicleId());

            // Allow NULL for driver_id if no driver is available
            if (driverId != -1) {
                ps.setInt(8, driverId);
            } else {
                ps.setNull(8, java.sql.Types.INTEGER);
            }

            ps.setDouble(9, booking.getFare());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking successfully saved.");
                if (driverId != -1) {
                    updateDriverStatus(driverId, "On Duty"); // Update driver status
                    System.out.println("Assigned driver ID: " + driverId);
                }
                connection.commit(); // Commit transaction
                return driverId; // Return the driver ID assigned
            } else {
                connection.rollback(); // Rollback if insertion fails
                throw new SQLException("Failed to save the booking.");
            }
        } catch (SQLException e) {
            connection.rollback(); // Rollback on exception
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restore auto-commit
        }
    }

    // Get an available driver ID, or return -1 if no drivers are available
    public int getAvailableDriverId() throws SQLException {
        String query = "SELECT id FROM drivers WHERE status = 'Available' LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                System.out.println("No available driver found.");
            }
        }
        return -1; // No available driver
    }

    // Update driver status (On Duty / Available)
    private void updateDriverStatus(int driverId, String status) throws SQLException {
        String query = "UPDATE drivers SET status = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, driverId);
            ps.executeUpdate();
        }
    }

    // This method should not be static because it's using the instance-level connection
    public List<Booking> getBookingsByCustomerId(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.order_number, b.customer_id, b.customer_name, b.customer_phone, " +
                "b.pickup_location_id, p.name AS pickup_location_name, " +
                "b.drop_location_id, d.name AS drop_location_name, " +
                "b.vehicle_id, v.type AS vehicle_type, " +
                "b.driver_id, b.fare " +
                "FROM bookings b " +
                "JOIN locations p ON b.pickup_location_id = p.id " +
                "JOIN locations d ON b.drop_location_id = d.id " +
                "JOIN vehicles v ON b.vehicle_id = v.id " +
                "WHERE b.customer_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Booking booking = new Booking(
                        resultSet.getString("order_number"),
                        resultSet.getInt("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_phone"),
                        resultSet.getInt("pickup_location_id"),
                        resultSet.getInt("drop_location_id"),
                        resultSet.getInt("vehicle_id"),
                        resultSet.getInt("driver_id"),
                        resultSet.getDouble("fare")
                );

                // Set the readable names
                booking.setPickupLocationName(resultSet.getString("pickup_location_name"));
                booking.setDropLocationName(resultSet.getString("drop_location_name"));
                booking.setVehicleName(resultSet.getString("vehicle_type"));

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // This method is non-static, ensuring proper access to the instance-level connection
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.order_number, b.customer_id, b.customer_name, b.customer_phone, " +
                "b.pickup_location_id, p.name AS pickup_location_name, " +
                "b.drop_location_id, d.name AS drop_location_name, " +
                "b.vehicle_id, v.type AS vehicle_type, " +
                "b.driver_id, b.fare " +
                "FROM bookings b " +
                "JOIN locations p ON b.pickup_location_id = p.id " +
                "JOIN locations d ON b.drop_location_id = d.id " +
                "JOIN vehicles v ON b.vehicle_id = v.id";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Booking booking = new Booking(
                        resultSet.getString("order_number"),
                        resultSet.getInt("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_phone"),
                        resultSet.getInt("pickup_location_id"),
                        resultSet.getInt("drop_location_id"),
                        resultSet.getInt("vehicle_id"),
                        resultSet.getInt("driver_id"),
                        resultSet.getDouble("fare")
                );

                // Set the readable names
                booking.setPickupLocationName(resultSet.getString("pickup_location_name"));
                booking.setDropLocationName(resultSet.getString("drop_location_name"));
                booking.setVehicleName(resultSet.getString("vehicle_type"));

                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Close connection method
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
