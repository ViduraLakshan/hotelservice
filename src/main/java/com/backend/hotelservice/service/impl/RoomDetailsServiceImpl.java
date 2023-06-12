package com.backend.hotelservice.service.impl;

import com.backend.hotelservice.entity.Property;
import com.backend.hotelservice.entity.RoomDetails;
import com.backend.hotelservice.exception.PropertyNotFoundException;
import com.backend.hotelservice.exception.RoomDetailsNotFoundException;
import com.backend.hotelservice.repository.PropertyRepository;
import com.backend.hotelservice.repository.RoomDetailsRepository;
import com.backend.hotelservice.service.RoomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private RoomDetailsRepository roomDetailsRepository;
    @Override
    public RoomDetails addNewRoom(UUID uuid, RoomDetails roomDetails) {
        Optional<Property> propertyOptional=propertyRepository.getByUuid(uuid);
        if (!propertyOptional.isPresent())
        {
            throw new PropertyNotFoundException("Property Not Found");
        }
        roomDetails.setProperty(propertyOptional.get());
        RoomDetails room=roomDetailsRepository.save(roomDetails);
        return roomDetails;
    }

    @Override
    public RoomDetails updateRoom(UUID uuid, RoomDetails roomDetails) {
        Optional<RoomDetails> roomDetailsOptional=roomDetailsRepository.getByUuid(uuid);
        if (!roomDetailsOptional.isPresent())
        {
            throw new RoomDetailsNotFoundException("RoomDetails Not Found");
        }
        RoomDetails roomDetailsFromDb=roomDetailsOptional.get();
        if (Objects.nonNull(roomDetails.getRoomType())&&
                !"".equalsIgnoreCase(roomDetails.getRoomType()))
        {
            roomDetailsFromDb.setRoomType(roomDetails.getRoomType());
        }
        if (Objects.nonNull(roomDetails.getAvailability()))
        {
            roomDetailsFromDb.setAvailability(roomDetails.getAvailability());
        }
        if (Objects.nonNull(roomDetails.getWhenAvailable()))
        {
            roomDetailsFromDb.setWhenAvailable(roomDetails.getWhenAvailable());
        }
        if (roomDetails.getNumberOfBed()!=0)
        {
            roomDetailsFromDb.setNumberOfBed(roomDetails.getNumberOfBed());
        }
        if (roomDetails.getPrice()!=0)
        {
            roomDetailsFromDb.setPrice(roomDetails.getPrice());
        }
        if (Objects.nonNull(roomDetails.getAcAvailability()))
        {
            roomDetailsFromDb.setAcAvailability(roomDetails.getAcAvailability());
        }
        if (Objects.nonNull(roomDetails.getAttachedBathroom()))
        {
            roomDetailsFromDb.setAttachedBathroom(roomDetails.getAttachedBathroom());
        }
        if (Objects.nonNull(roomDetails.getDvdAvailability()))
        {
            roomDetailsFromDb.setDvdAvailability(roomDetails.getDvdAvailability());
        }
        if (Objects.nonNull(roomDetails.getTvAvailability()))
        {
            roomDetailsFromDb.setTvAvailability(roomDetails.getTvAvailability());
        }
        if (Objects.nonNull(roomDetails.getWifiAvailability()))
        {
            roomDetailsFromDb.setWifiAvailability(roomDetails.getWifiAvailability());
        }
        if (Objects.nonNull(roomDetails.getPatio()))
        {
            roomDetailsFromDb.setPatio(roomDetails.getPatio());
        }
        if (Objects.nonNull(roomDetails.getSize())&&
                !"".equalsIgnoreCase(roomDetails.getSize()))
        {
            roomDetailsFromDb.setSize(roomDetails.getSize());
        }
        roomDetailsRepository.save(roomDetailsFromDb);
        return roomDetailsFromDb;
    }

    @Override
    public List<RoomDetails> getRoomDetails(UUID uuid) {
        Optional<Property> propertyOptional=propertyRepository.getByUuid(uuid);
        if (!propertyOptional.isPresent())
        {
            throw new PropertyNotFoundException("Property Not Found");
        }
        List<RoomDetails> roomDetails=roomDetailsRepository.getByProperty(propertyOptional.get());
        return roomDetails;
    }

    @Override
    public void deleteRoomDetails(UUID uuid) {
        Optional<RoomDetails> roomDetails=roomDetailsRepository.getByUuid(uuid);
        if (!roomDetails.isPresent())
        {
            throw new RoomDetailsNotFoundException("RoomDetails Not Found");
        }
        roomDetailsRepository.deleteById(roomDetails.get().getId());
    }

}