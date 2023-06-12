package com.backend.hotelservice.service;

import com.backend.hotelservice.entity.RoomDetails;

import java.util.List;
import java.util.UUID;

public interface RoomDetailsService {
    RoomDetails addNewRoom(UUID uuid, RoomDetails roomDetails);

    RoomDetails updateRoom(UUID uuid, RoomDetails roomDetails);

    List<RoomDetails> getRoomDetails(UUID uuid);

    void deleteRoomDetails(UUID uuid);
}
