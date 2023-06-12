package com.backend.hotelservice.dto;

import com.backend.hotelservice.entity.Property;
import com.backend.hotelservice.entity.RoomDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@ToString
public class RoomBookDetailsDto {
    private String userName;
    private String phoneNo;
    private String email;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int totalStay;
    private LocalTime arrivalTime;
    private RoomDetails roomDetails;
    private Property property;
}
