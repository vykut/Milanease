package com.example.milanease.data;

public class UsernameException extends Exception {
    public UsernameException(String message) {
        super(message);
    }

    public UsernameException() {
        super("Username already exists");
    }
}
