package com.backend.hotelservice.service.impl;


import com.backend.hotelservice.entity.Property;
import com.backend.hotelservice.repository.PropertyRepository;
import com.backend.hotelservice.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyServiceImpl implements PropertyService {

//    @Autowired
//    private PropertyOwnerRepository propertyOwnerRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Override
    public Property addNewProperty(Long ownerId, Property property) {
//        PropertyOwner propertyOwner =propertyOwnerRepository.getById(ownerId);
//        property.setPropertyOwner(propertyOwner);
//        Property property1 = propertyRepository.save(property);
//        return property;
        propertyRepository.save(property);
        return property;
    }

    @Override
    public Property getProperty(UUID propertyId) {
        Optional<Property> propertyOptional=propertyRepository.getByUuid(propertyId);
        return propertyOptional.get();
    }

    @Override
    public List<Property> getAllProperty() {

        return propertyRepository.findAll();
    }

}
