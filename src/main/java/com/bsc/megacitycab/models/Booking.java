package com.bsc.megacitycab.models;

public class Booking {
    private int customerId;         // customer_id (int)
    private String orderNumber;
    private String customerName;
    private String customerPhone;
    private int pickupLocationId;   // pickup_location_id (int)
    private int dropLocationId;     // drop_location_id (int)
    private int vehicleId;          // vehicle_id (int)
    private double fare;

    // Constructor using customer_id, location IDs, and vehicle_id
    public Booking(String orderNumber, Integer customerId, String customerName, String customerPhone,
                   int pickupLocationId, int dropLocationId, int vehicleId, double fare) {
        this.customerId = customerId;
        //this.customerId = this.customerId; // customer_id is now an int
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.pickupLocationId = pickupLocationId; // pickup_location_id is int
        this.dropLocationId = dropLocationId;     // drop_location_id is int
        this.vehicleId = vehicleId;               // vehicle_id is int
        this.fare = fare;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getPickupLocationId() {
        return pickupLocationId;
    }

    public void setPickupLocationId(int pickupLocationId) {
        this.pickupLocationId = pickupLocationId;
    }

    public int getDropLocationId() {
        return dropLocationId;
    }

    public void setDropLocationId(int dropLocationId) {
        this.dropLocationId = dropLocationId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    // Optional: Override toString for easier debugging/logging
    @Override
    public String toString() {
        return "Booking{" +
                "customerId=" + customerId +
                ", orderNumber='" + orderNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", pickupLocationId=" + pickupLocationId +
                ", dropLocationId=" + dropLocationId +
                ", vehicleId=" + vehicleId +
                ", fare=" + fare +
                '}';
    }
}
