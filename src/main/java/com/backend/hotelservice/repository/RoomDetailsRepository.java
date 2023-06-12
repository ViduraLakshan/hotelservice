package com.backend.hotelservice.repository;

import com.backend.hotelservice.entity.Property;
import com.backend.hotelservice.entity.RoomDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomDetailsRepository extends JpaRepository<RoomDetails,Long> {

    Page<RoomDetails> getByProperty(Property property, Pageable pageable);
    List<RoomDetails> getByProperty(Property property);
    Optional<RoomDetails> getByUuid(UUID uuid);
}

