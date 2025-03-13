package com.bsc.megacitycab.models;

public class Vehicle {
    private int id;
    private String type;
    private String status; // Added status field

    // Constructor to initialize the Vehicle object
    public Vehicle( int id, String type, String status) {
        this.id = id;
        this.type = type;
        this.status = status; // Initialize status
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for type
    public String getType() {
        return type;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status (if needed for updates)
    public void setStatus(String status) {
        this.status = status;
    }
}
