package com.backend.hotelservice.repository;

import com.backend.hotelservice.entity.Property;
import com.backend.hotelservice.entity.RoomBookDetails;
import com.backend.hotelservice.entity.RoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomBookRepository extends JpaRepository<RoomBookDetails,Long> {
    List<RoomBookDetails> findAllByProperty(Property property);

    List<RoomBookDetails> findAllByRoomDetails(RoomDetails roomDetails);

    Optional<RoomBookDetails> getByEmail(String email);
}

