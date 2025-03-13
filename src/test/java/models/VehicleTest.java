package models;

import com.bsc.megacitycab.models.Vehicle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testVehicleConstructor() {
        Vehicle vehicle = new Vehicle(1, "Car", "Available");
        assertEquals(1, vehicle.getId(), "ID should be initialized correctly");
        assertEquals("Car", vehicle.getType(), "Type should be initialized correctly");
        assertEquals("Available", vehicle.getStatus(), "Status should be initialized correctly");
    }

    @Test
    void testSetStatus() {
        Vehicle vehicle = new Vehicle(2, "Bike", "Available");
        vehicle.setStatus("Booked");
        assertEquals("Booked", vehicle.getStatus(), "Status should be updated correctly");
    }
}
