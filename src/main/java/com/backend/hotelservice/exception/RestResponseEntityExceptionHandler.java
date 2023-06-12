package com.backend.hotelservice.exception;


import com.backend.hotelservice.model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RoomDetailsNotFoundException.class)
    public ResponseEntity<ErrorMessage> roomDetailsNotFoundException(RoomDetailsNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setTimestamp(LocalDateTime.now());
        message.setMessage(ex.getMessage());
        message.setDetail(request.getDescription(false));
        message.setPath(request.getContextPath());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<ErrorMessage> roomNotAvailableException(RoomNotAvailableException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setTimestamp(LocalDateTime.now());
        message.setMessage(ex.getMessage());
        message.setDetail(request.getDescription(false));
        message.setPath(request.getContextPath());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<ErrorMessage> propertyNotFoundException(PropertyNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage();
        message.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setTimestamp(LocalDateTime.now());
        message.setMessage(ex.getMessage());
        message.setDetail(request.getDescription(false));
        message.setPath(request.getContextPath());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}

