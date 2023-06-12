package com.backend.hotelservice.service;

import com.backend.hotelservice.dto.RoomBookDetailsDto;
import com.backend.hotelservice.entity.RoomBookDetails;

import java.util.List;
import java.util.UUID;

public interface RoomBookService {
    void BookRoom(UUID roomDetailsUuid, RoomBookDetails roomBookDetails);

    List<RoomBookDetailsDto> getRoomBookOfProperty(UUID propertyUuid);

    List<RoomBookDetailsDto> getRoomBookOfRoom(UUID roomUuid);
}