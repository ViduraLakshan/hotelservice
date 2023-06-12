package com.backend.hotelservice.service.impl;

import com.backend.hotelservice.Utils.ModelMapperUtil;
import com.backend.hotelservice.dto.RoomBookDetailsDto;
import com.backend.hotelservice.entity.Property;
import com.backend.hotelservice.entity.RoomBookDetails;
import com.backend.hotelservice.entity.RoomDetails;
import com.backend.hotelservice.exception.PropertyNotFoundException;
import com.backend.hotelservice.exception.RoomDetailsNotFoundException;
import com.backend.hotelservice.exception.RoomNotAvailableException;
import com.backend.hotelservice.model.Availability;
import com.backend.hotelservice.repository.PropertyRepository;
import com.backend.hotelservice.repository.RoomBookRepository;
import com.backend.hotelservice.repository.RoomDetailsRepository;
import com.backend.hotelservice.service.RoomBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomBookServiceImpl implements RoomBookService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private RoomDetailsRepository roomDetailsRepository;
    @Autowired
    private RoomBookRepository roomBookRepository;
    @Override
    public void BookRoom(UUID roomDetailsUuid, RoomBookDetails roomBookDetails) {
        Optional<RoomDetails> roomDetailsOptional=roomDetailsRepository.getByUuid(roomDetailsUuid);
        if (!roomDetailsOptional.isPresent())
        {
            throw new RoomDetailsNotFoundException("Room Details Not Found");
        }
        if (roomDetailsOptional.get().getAvailability()== Availability.NO)
        {
            if (roomDetailsOptional.get().getWhenAvailable().isBefore(roomBookDetails.getCheckIn()))
            {
                throw new RoomNotAvailableException("Room Not Available");
            }
        }

        roomBookDetails.setRoomDetails(roomDetailsOptional.get());
        roomBookDetails.setProperty(roomDetailsOptional.get().getProperty());
        roomBookRepository.save(roomBookDetails);
    }

    @Override
    public List<RoomBookDetailsDto> getRoomBookOfProperty(UUID propertyUuid) {
        Optional<Property> propertyOptional =propertyRepository.getByUuid(propertyUuid);
        if (!propertyOptional.isPresent())
        {
            throw new PropertyNotFoundException("Property Not Found");
        }
        List<RoomBookDetails>roomBookDetailsList=roomBookRepository.findAllByProperty(propertyOptional.get());
        List<RoomBookDetailsDto>roomBookDetailsDtoList= ModelMapperUtil.mapList(roomBookDetailsList,RoomBookDetailsDto.class);
        return roomBookDetailsDtoList;
    }

    @Override
    public List<RoomBookDetailsDto> getRoomBookOfRoom(UUID roomUuid) {
        Optional<RoomDetails> roomDetailsOptional =roomDetailsRepository.getByUuid(roomUuid);
        if (!roomDetailsOptional.isPresent())
        {
            throw new RoomDetailsNotFoundException("Room Details Not Found");
        }
        List<RoomBookDetails>roomBookDetailsList=roomBookRepository.findAllByRoomDetails(roomDetailsOptional.get());
        List<RoomBookDetailsDto>roomBookDetailsDtoList= ModelMapperUtil.mapList(roomBookDetailsList,RoomBookDetailsDto.class);
        return roomBookDetailsDtoList;
    }
}