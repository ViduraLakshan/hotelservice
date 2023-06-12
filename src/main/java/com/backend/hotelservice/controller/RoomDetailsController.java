package com.backend.hotelservice.controller;


import com.backend.hotelservice.entity.RoomDetails;
import com.backend.hotelservice.service.RoomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/roomDetails")
public class RoomDetailsController {

    @Autowired
    private RoomDetailsService roomDetailsService;

    @PostMapping("/add/{propertyUuid}")
    public ResponseEntity<RoomDetails> addNewRoom(@PathVariable("propertyUuid") UUID uuid , @Valid @RequestBody RoomDetails roomDetails)
    {
        RoomDetails room=roomDetailsService.addNewRoom(uuid, roomDetails);
        return ResponseEntity.ok(room);
    }
    @PutMapping("/update/{roomId}")
    public ResponseEntity<RoomDetails> updateRoom(@PathVariable("roomId") UUID uuid,@Valid @RequestBody RoomDetails roomDetails)
    {
        RoomDetails room=roomDetailsService.updateRoom(uuid, roomDetails);
        return ResponseEntity.ok(room);
    }
    @PostMapping("/add/{ownerId}")
    @GetMapping("/get/{propertyUuid}")
    public ResponseEntity<List<RoomDetails>> getRoomDetails(@PathVariable("propertyUuid") UUID uuid)
    {
        List<RoomDetails> room=roomDetailsService.getRoomDetails(uuid);
        return ResponseEntity.ok(room);
    }
    @DeleteMapping("/delete/{roomUuid}")
    public ResponseEntity<Void> deleteRoomDetails(@PathVariable("roomUuid") UUID uuid)
    {
        roomDetailsService.deleteRoomDetails(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
