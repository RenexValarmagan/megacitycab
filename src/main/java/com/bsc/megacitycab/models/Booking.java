package com.bsc.megacitycab.models;

public class Booking {
    private int customerId;
    private String orderNumber;
    private String customerName;
    private String customerPhone;
    private int pickupLocationId;
    private int dropLocationId;
    private int vehicleId;
    private Integer driverId;
    private double fare;

    // New fields for readable names
    private String pickupLocationName;
    private String dropLocationName;
    private String vehicleName;

    // Constructor including new fields
    public Booking(String orderNumber, int customerId, String customerName, String customerPhone,
                   int pickupLocationId, int dropLocationId, int vehicleId, Integer driverId, double fare) {
        this.customerId = customerId;
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.pickupLocationId = pickupLocationId;
        this.dropLocationId = dropLocationId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.fare = fare;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public int getPickupLocationId() { return pickupLocationId; }
    public void setPickupLocationId(int pickupLocationId) { this.pickupLocationId = pickupLocationId; }

    public int getDropLocationId() { return dropLocationId; }
    public void setDropLocationId(int dropLocationId) { this.dropLocationId = dropLocationId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public Integer getDriverId() { return driverId; }
    public void setDriverId(Integer driverId) { this.driverId = driverId; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    // Getters and Setters for new fields
    public String getPickupLocationName() { return pickupLocationName; }
    public void setPickupLocationName(String pickupLocationName) { this.pickupLocationName = pickupLocationName; }

    public String getDropLocationName() { return dropLocationName; }
    public void setDropLocationName(String dropLocationName) { this.dropLocationName = dropLocationName; }

    public String getVehicleName() { return vehicleName; }
    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

    // Override toString for debugging
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
                ", driverId=" + driverId +
                ", fare=" + fare +
                ", pickupLocationName='" + pickupLocationName + '\'' +
                ", dropLocationName='" + dropLocationName + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                '}';
    }
}
