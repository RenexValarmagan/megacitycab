package com.bsc.megacitycab.models;

public class Driver {
    private int id;
    private String name;
    private String phone;
    private int vehicleId;
    private String status;

    // Constructor
    public Driver(int id, String name, String phone, int vehicleId, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.vehicleId = vehicleId;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
