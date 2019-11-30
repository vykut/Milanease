package com.example.milanease.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String email;
    private String clientID;
    private String displayName;

    public LoggedInUser(String displayName, String email, String clientID) {
        this.email = email;
        this.clientID = clientID;
        this.displayName = displayName;
    }

    public String getClientID() {
        return clientID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}
