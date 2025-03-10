package com.bsc.megacitycab.models;

public class Booking {
    private int customerId;         // customer_id (int)
    private String orderNumber;
    private String customerName;
    private String customerPhone;
    private int pickupLocationId;   // pickup_location_id (int)
    private int dropLocationId;     // drop_location_id (int)
    private int vehicleId;          // vehicle_id (int)
    private Integer driverId;       // driver_id (Integer), can be NULL in DB
    private double fare;

    // Constructor including driverId
    public Booking(String orderNumber, int customerId, String customerName, String customerPhone,
                   int pickupLocationId, int dropLocationId, int vehicleId, Integer driverId, double fare) {
        this.customerId = customerId;
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.pickupLocationId = pickupLocationId;
        this.dropLocationId = dropLocationId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;  // New field added
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

    public Integer getDriverId() {
        return driverId;  // Return Integer, which can be null
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    // Override toString for debugging/logging
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
                ", driverId=" + driverId +  // Added driverId
                ", fare=" + fare +
                '}';
    }
}
