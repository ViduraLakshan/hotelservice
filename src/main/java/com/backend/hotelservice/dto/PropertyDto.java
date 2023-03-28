package com.backend.hotelservice.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@ToString
public class PropertyDto {
    private UUID uuid;
    private String name;
    private String location;
//    private PropertyOwner propertyOwner;
//    private List<RoomDetails> roomDetails;
}
