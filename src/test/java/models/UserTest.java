package models;

import com.bsc.megacitycab.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserConstructor() {
        // Arrange
        int id = 101;
        String username = "admin";
        String password = "securePass";
        String role = "Admin";

        // Act
        User user = new User(id, username, password, role);

        // Assert
        assertEquals(id, user.getId(), "ID should be initialized correctly");
        assertEquals(username, user.getUsername(), "Username should be initialized correctly");
        assertEquals(password, user.getPassword(), "Password should be initialized correctly");
        assertEquals(role, user.getRole(), "Role should be initialized correctly");
    }
}
