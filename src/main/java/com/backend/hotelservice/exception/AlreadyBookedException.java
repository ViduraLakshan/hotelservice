package com.backend.hotelservice.exception;

public class AlreadyBookedException extends RuntimeException{
    public AlreadyBookedException() {
        super();
    }

    public AlreadyBookedException(String message) {
        super(message);
    }

    public AlreadyBookedException(String message, Throwable cause) {
        super(message, cause);
    }
}

