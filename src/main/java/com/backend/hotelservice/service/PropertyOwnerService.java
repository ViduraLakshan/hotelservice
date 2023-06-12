package com.backend.hotelservice.service;

import com.backend.hotelservice.entity.PropertyOwner;
import org.springframework.data.domain.Page;

public interface PropertyOwnerService {
    PropertyOwner addNewOwner(PropertyOwner propertyOwner);

    Page<PropertyOwner> getAll(int page, int size);
}
