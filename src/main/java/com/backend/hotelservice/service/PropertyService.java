package com.backend.hotelservice.service;

import com.backend.hotelservice.entity.Property;

import java.util.List;
import java.util.UUID;

public interface PropertyService {
    Property addNewProperty(Long ownerId, Property property);

    Property getProperty(UUID propertyId);

    List<Property> getAllProperty();
}
