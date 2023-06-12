package com.backend.hotelservice.repository;

import com.backend.hotelservice.entity.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyOwnerRepository extends JpaRepository<PropertyOwner,Long> {
}
