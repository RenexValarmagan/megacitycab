package com.bsc.megacitycab.models;

public class Customer {
    private int id; // 🔹 Add this field
    private String customerRegNumber;
    private String name;
    private String address;
    private String nic;
    private String username;
    private String password;
    private String phone;

    // Constructor including ID
    public Customer(int id, String customerRegNumber, String name, String address, String nic, String username, String password, String phone) {
        this.id = id;
        this.customerRegNumber = customerRegNumber;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    // Constructor without password
    public Customer(int id, String customerRegNumber, String name, String address, String nic, String username, String phone) {
        this.id = id;
        this.customerRegNumber = customerRegNumber;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.username = username;
        this.phone = phone;
    }

    // Getters
    public int getId() { return id; } // 🔹 Add this getter
    public String getCustomerRegNumber() { return customerRegNumber; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getNic() { return nic; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }

    // Setters
    public void setId(int id) { this.id = id; } // 🔹 Add this setter
    public void setCustomerRegNumber(String customerRegNumber) { this.customerRegNumber = customerRegNumber; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setNic(String nic) { this.nic = nic; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setPhone(String phone) { this.phone = phone; }

    // toString for debugging
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerRegNumber='" + customerRegNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
