package com.backend.hotelservice.service.impl;


import com.backend.hotelservice.entity.PropertyOwner;
import com.backend.hotelservice.repository.PropertyOwnerRepository;
import com.backend.hotelservice.service.PropertyOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PropertyOwnerServiceImpl implements PropertyOwnerService {

    @Autowired
    private PropertyOwnerRepository propertyOwnerRepository;
    @Override
    public PropertyOwner addNewOwner(PropertyOwner propertyOwner) {
        PropertyOwner owner=propertyOwnerRepository.save(propertyOwner);
        return owner;
    }

    @Override
    public Page<PropertyOwner> getAll(int page, int size) {
        return propertyOwnerRepository.findAll(PageRequest.of(page,size));
    }
}