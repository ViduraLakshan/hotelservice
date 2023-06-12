package com.backend.hotelservice.controller;

import com.backend.hotelservice.dto.RoomBookDetailsDto;
import com.backend.hotelservice.entity.RoomBookDetails;
import com.backend.hotelservice.service.RoomBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/booking")
public class RoomBookController {
    @Autowired
    private RoomBookService roomBookService;

    @PostMapping("/add/{roomDetailsId}")
    public ResponseEntity<Void> BookRoom(@PathVariable("roomDetailsId") UUID roomDetailsUuid, @RequestBody RoomBookDetails roomBookDetails)
    {
        roomBookService.BookRoom(roomDetailsUuid,roomBookDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getByProperty/{propertyUuid}")
    public ResponseEntity<List<RoomBookDetailsDto>> getRoomBookOfProperty(@PathVariable("propertyUuid") UUID propertyUuid)
    {
        List<RoomBookDetailsDto>roomBookDetails=roomBookService.getRoomBookOfProperty(propertyUuid);
        return ResponseEntity.ok(roomBookDetails);
    }
    @GetMapping("/getByRoom/{roomUuid}")
    public ResponseEntity<List<RoomBookDetailsDto>> getRoomBookOfRoom(@PathVariable("roomUuid") UUID roomUuid)
    {
        List<RoomBookDetailsDto>roomBookDetails=roomBookService.getRoomBookOfRoom(roomUuid);
        return ResponseEntity.ok(roomBookDetails);
    }
}