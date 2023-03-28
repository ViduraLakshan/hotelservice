package com.backend.hotelservice.repository;

import com.backend.hotelservice.entity.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property,Long> {

    Optional<Property> getByUuid(UUID uuid);

    Page<Property> getByLocation(String location, Pageable pageable);
}
