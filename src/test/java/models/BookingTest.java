package models;

import com.bsc.megacitycab.models.Booking;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void testBookingConstructor() {
        // Arrange
        String orderNumber = "ORD123456";
        int customerId = 1;
        String customerName = "John Doe";
        String customerPhone = "0712345678";
        int pickupLocationId = 101;
        int dropLocationId = 202;
        int vehicleId = 5;
        Integer driverId = 10;
        double fare = 1500.00;

        // Act
        Booking booking = new Booking(orderNumber, customerId, customerName, customerPhone, pickupLocationId, dropLocationId, vehicleId, driverId, fare);

        // Assert
        assertEquals(orderNumber, booking.getOrderNumber(), "Order number should be initialized correctly");
        assertEquals(customerId, booking.getCustomerId(), "Customer ID should be initialized correctly");
        assertEquals(customerName, booking.getCustomerName(), "Customer name should be initialized correctly");
        assertEquals(customerPhone, booking.getCustomerPhone(), "Customer phone should be initialized correctly");
        assertEquals(pickupLocationId, booking.getPickupLocationId(), "Pickup location ID should be initialized correctly");
        assertEquals(dropLocationId, booking.getDropLocationId(), "Drop location ID should be initialized correctly");
        assertEquals(vehicleId, booking.getVehicleId(), "Vehicle ID should be initialized correctly");
        assertEquals(driverId, booking.getDriverId(), "Driver ID should be initialized correctly");
        assertEquals(fare, booking.getFare(), "Fare should be initialized correctly");
    }

    @Test
    void testSetters() {
        // Arrange
        Booking booking = new Booking("ORD654321", 2, "Jane Doe", "0723456789", 303, 404, 8, 12, 1800.00);

        // Act
        booking.setOrderNumber("ORD999999");
        booking.setCustomerId(3);
        booking.setCustomerName("Alice Smith");
        booking.setCustomerPhone("0756789012");
        booking.setPickupLocationId(505);
        booking.setDropLocationId(606);
        booking.setVehicleId(9);
        booking.setDriverId(15);
        booking.setFare(2000.50);

        // Assert
        assertEquals("ORD999999", booking.getOrderNumber(), "Order number should be updated correctly");
        assertEquals(3, booking.getCustomerId(), "Customer ID should be updated correctly");
        assertEquals("Alice Smith", booking.getCustomerName(), "Customer name should be updated correctly");
        assertEquals("0756789012", booking.getCustomerPhone(), "Customer phone should be updated correctly");
        assertEquals(505, booking.getPickupLocationId(), "Pickup location ID should be updated correctly");
        assertEquals(606, booking.getDropLocationId(), "Drop location ID should be updated correctly");
        assertEquals(9, booking.getVehicleId(), "Vehicle ID should be updated correctly");
        assertEquals(15, booking.getDriverId(), "Driver ID should be updated correctly");
        assertEquals(2000.50, booking.getFare(), "Fare should be updated correctly");
    }

    @Test
    void testNewFieldsSettersAndGetters() {
        // Arrange
        Booking booking = new Booking("ORD777777", 4, "Bob Johnson", "0767890123", 707, 808, 10, null, 2500.75);

        // Act
        booking.setPickupLocationName("Colombo Fort");
        booking.setDropLocationName("Bambalapitiya");
        booking.setVehicleName("Toyota Prius");

        // Assert
        assertEquals("Colombo Fort", booking.getPickupLocationName(), "Pickup location name should be updated correctly");
        assertEquals("Bambalapitiya", booking.getDropLocationName(), "Drop location name should be updated correctly");
        assertEquals("Toyota Prius", booking.getVehicleName(), "Vehicle name should be updated correctly");
    }

    @Test
    void testDriverIdCanBeNull() {
        // Arrange & Act
        Booking booking = new Booking("ORD888888", 5, "Charlie Brown", "0778901234", 909, 1001, 12, null, 3000.00);

        // Assert
        assertNull(booking.getDriverId(), "Driver ID should be null if not assigned");
    }
}
