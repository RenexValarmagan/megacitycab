package com.bsc.megacitycab.models;

public class Vehicle {
    private int id;
    private String type;

    public Vehicle(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
