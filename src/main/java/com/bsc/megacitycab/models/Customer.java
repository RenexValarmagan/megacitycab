package com.bsc.megacitycab.models;

public class Customer {
    private String customerRegNumber;
    private String name;
    private String address;
    private String nic;
    private String username;
    private String password;
    private String phone;

    // Constructor for creating a Customer object (includes password)
    public Customer(String customerRegNumber, String name, String address, String nic, String username, String password) {
        this.customerRegNumber = customerRegNumber;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.username = username;
        this.password = password;
    }

    // Constructor for creating a Customer object (without password)
    public Customer(String customerRegNumber, String name, String address, String nic, String username) {
        this.customerRegNumber = customerRegNumber;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.username = username;
    }

    // Getters
    public String getCustomerRegNumber() { return customerRegNumber; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getNic() { return nic; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }

    // Setters (optional, if you need to update customer details)
    public void setCustomerRegNumber(String customerRegNumber) { this.customerRegNumber = customerRegNumber; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setNic(String nic) { this.nic = nic; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    // Optional: Override toString for easier debugging/logging
    @Override
    public String toString() {
        return "Customer{" +
                "customerRegNumber='" + customerRegNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
