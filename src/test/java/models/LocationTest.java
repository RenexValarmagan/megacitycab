package models;

import com.bsc.megacitycab.models.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void testLocationConstructor() {
        // Arrange
        int id = 1;
        String name = "Colombo";

        // Act
        Location location = new Location(id, name);

        // Assert
        assertEquals(id, location.getId(), "ID should be initialized correctly");
        assertEquals(name, location.getName(), "Name should be initialized correctly");
    }
}
