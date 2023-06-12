package com.backend.hotelservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@ToString
public class RoomDetailsDto {
    private UUID uuid;
    private String roomType;
    @Enumerated(EnumType.ORDINAL)
    private Availability availability;
    private LocalDate whenAvailable;
    private int numberOfBed;
    private float price;
    @Enumerated(EnumType.ORDINAL)
    private Availability acAvailability;
    @Enumerated(EnumType.ORDINAL)
    private Availability attachedBathroom;
    @Enumerated(EnumType.ORDINAL)
    private Availability patio;
    @Enumerated(EnumType.ORDINAL)
    private Availability tvAvailability;
    @Enumerated(EnumType.ORDINAL)
    private Availability dvdAvailability;
    @Enumerated(EnumType.ORDINAL)
    private Availability wifiAvailability;
    private String size;
    private Property property;
}
