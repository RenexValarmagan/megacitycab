package com.bsc.megacitycab.models;

public class Booking {
    private String orderNumber;
    private String name;
    private String address;
    private String phone;
    private String destination;
    private double fare;

    public Booking(String orderNumber, String name, String address, String phone, String destination, double fare) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.destination = destination;
        this.fare = fare;
    }

    public String getOrderNumber() { return orderNumber; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getDestination() { return destination; }
    public double getFare() { return fare; }
}
