package com.backend.hotelservice.exception;

public class RoomDetailsNotFoundException extends RuntimeException{
    public RoomDetailsNotFoundException() {
        super();
    }

    public RoomDetailsNotFoundException(String message) {
        super(message);
    }

    public RoomDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
