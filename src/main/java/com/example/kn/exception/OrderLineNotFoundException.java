package com.example.kn.exception;

public class OrderLineNotFoundException extends Exception {
    private String message;


    public OrderLineNotFoundException(String message) {
        super(message);

    }
}
