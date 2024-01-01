package com.research.todoapplication.exception;

public class CustomException extends Exception {

    private final String details;

    public CustomException(String message, String details) {
        super(message);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return super.toString() + ", Details: " + details;
    }
}