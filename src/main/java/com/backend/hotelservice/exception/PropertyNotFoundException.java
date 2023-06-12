package com.backend.hotelservice.exception;

public class PropertyNotFoundException extends RuntimeException{
    public PropertyNotFoundException() {
        super();
    }

    public PropertyNotFoundException(String message) {
        super(message);
    }

    public PropertyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}