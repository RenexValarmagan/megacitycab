package models;

import com.bsc.megacitycab.models.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomerConstructorWithPassword() {
        // Arrange
        int id = 1;
        String customerRegNumber = "CRN12345";
        String name = "John Doe";
        String address = "123 Main St, Colombo";
        String nic = "987654321V";
        String username = "johndoe";
        String password = "securePass";
        String phone = "0712345678";

        // Act
        Customer customer = new Customer(id, customerRegNumber, name, address, nic, username, password, phone);

        // Assert
        assertEquals(id, customer.getId(), "ID should be initialized correctly");
        assertEquals(customerRegNumber, customer.getCustomerRegNumber(), "Customer Reg Number should be initialized correctly");
        assertEquals(name, customer.getName(), "Name should be initialized correctly");
        assertEquals(address, customer.getAddress(), "Address should be initialized correctly");
        assertEquals(nic, customer.getNic(), "NIC should be initialized correctly");
        assertEquals(username, customer.getUsername(), "Username should be initialized correctly");
        assertEquals(password, customer.getPassword(), "Password should be initialized correctly");
        assertEquals(phone, customer.getPhone(), "Phone should be initialized correctly");
    }

    @Test
    void testCustomerConstructorWithoutPassword() {
        // Arrange
        int id = 2;
        String customerRegNumber = "CRN67890";
        String name = "Jane Doe";
        String address = "456 Park St, Colombo";
        String nic = "123456789V";
        String username = "janedoe";
        String phone = "0723456789";

        // Act
        Customer customer = new Customer(id, customerRegNumber, name, address, nic, username, phone);

        // Assert
        assertEquals(id, customer.getId(), "ID should be initialized correctly");
        assertEquals(customerRegNumber, customer.getCustomerRegNumber(), "Customer Reg Number should be initialized correctly");
        assertEquals(name, customer.getName(), "Name should be initialized correctly");
        assertEquals(address, customer.getAddress(), "Address should be initialized correctly");
        assertEquals(nic, customer.getNic(), "NIC should be initialized correctly");
        assertEquals(username, customer.getUsername(), "Username should be initialized correctly");
        assertNull(customer.getPassword(), "Password should be null when not set");
        assertEquals(phone, customer.getPhone(), "Phone should be initialized correctly");
    }

    @Test
    void testSetters() {
        // Arrange
        Customer customer = new Customer(3, "CRN99999", "Alice", "789 Ocean Rd", "567890123V", "alice123", "0734567890");

        // Act
        customer.setId(10);
        customer.setCustomerRegNumber("CRN11111");
        customer.setName("Alice Smith");
        customer.setAddress("789 Beach Rd");
        customer.setNic("567890000V");
        customer.setUsername("alice_smith");
        customer.setPassword("newPassword");
        customer.setPhone("0745678901");

        // Assert
        assertEquals(10, customer.getId(), "ID should be updated correctly");
        assertEquals("CRN11111", customer.getCustomerRegNumber(), "Customer Reg Number should be updated correctly");
        assertEquals("Alice Smith", customer.getName(), "Name should be updated correctly");
        assertEquals("789 Beach Rd", customer.getAddress(), "Address should be updated correctly");
        assertEquals("567890000V", customer.getNic(), "NIC should be updated correctly");
        assertEquals("alice_smith", customer.getUsername(), "Username should be updated correctly");
        assertEquals("newPassword", customer.getPassword(), "Password should be updated correctly");
        assertEquals("0745678901", customer.getPhone(), "Phone should be updated correctly");
    }
}
