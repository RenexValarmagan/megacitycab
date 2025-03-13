package models;

import com.bsc.megacitycab.models.Driver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void testDriverConstructor() {
        // Arrange
        int id = 1;
        String name = "John Doe";
        String phone = "0712345678";
        int vehicleId = 101;
        String status = "Available";

        // Act
        Driver driver = new Driver(id, name, phone, vehicleId, status);

        // Assert
        assertEquals(id, driver.getId(), "ID should be initialized correctly");
        assertEquals(name, driver.getName(), "Name should be initialized correctly");
        assertEquals(phone, driver.getPhone(), "Phone should be initialized correctly");
        assertEquals(vehicleId, driver.getVehicleId(), "Vehicle ID should be initialized correctly");
        assertEquals(status, driver.getStatus(), "Status should be initialized correctly");
    }

    @Test
    void testSetters() {
        // Arrange
        Driver driver = new Driver(2, "Jane Doe", "0723456789", 102, "Unavailable");

        // Act
        driver.setId(3);
        driver.setName("Alice Smith");
        driver.setPhone("0734567890");
        driver.setVehicleId(103);
        driver.setStatus("Available");

        // Assert
        assertEquals(3, driver.getId(), "ID should be updated correctly");
        assertEquals("Alice Smith", driver.getName(), "Name should be updated correctly");
        assertEquals("0734567890", driver.getPhone(), "Phone should be updated correctly");
        assertEquals(103, driver.getVehicleId(), "Vehicle ID should be updated correctly");
        assertEquals("Available", driver.getStatus(), "Status should be updated correctly");
    }
}
