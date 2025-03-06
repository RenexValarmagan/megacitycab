package com.bsc.megacitycab.models;

public class User {
    private int id; // Add user ID
    private String username;
    private String password;
    private String role;

    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() { return id; } // Getter for ID
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
