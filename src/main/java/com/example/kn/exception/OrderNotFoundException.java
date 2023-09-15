package com.example.kn.exception;

public class OrderNotFoundException extends Exception {
    private String message;


    public OrderNotFoundException(String message) {
        super(message);

    }
}
