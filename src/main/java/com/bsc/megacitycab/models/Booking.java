package com.bsc.megacitycab.models;

public class Booking {
    private String orderNumber;
    private int customerId;
    private String customerAddress; // New field for customer address
    private String customerPhoneNumber; // New field for customer phone number
    private int pickupLocationId;
    private int dropLocationId;
    private int vehicleId;
    private int driverId;
    private double fare;

    // Updated constructor with customer address and phone number
    public Booking(String orderNumber, int customerId, String customerAddress, String customerPhoneNumber,
                   int pickupLocationId, int dropLocationId, int vehicleId, int driverId, double fare) {
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.pickupLocationId = pickupLocationId;
        this.dropLocationId = dropLocationId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.fare = fare;
    }

    // Getters and setters for new fields
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    // Existing getters
    public String getOrderNumber() {
        return orderNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getPickupLocationId() {
        return pickupLocationId;
    }

    public int getDropLocationId() {
        return dropLocationId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public int getDriverId() {
        return driverId;
    }

    public double getFare() {
        return fare;
    }
}
