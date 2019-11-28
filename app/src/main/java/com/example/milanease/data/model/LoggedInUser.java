package com.example.milanease.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private String address;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
        //de modificat acest bullshit :))))))
        address = "acasa";
    }

    public LoggedInUser(String userId, String displayName, String address) {
        this.userId = userId;
        this.displayName = displayName;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAddress() {
        return address;
    }
}
