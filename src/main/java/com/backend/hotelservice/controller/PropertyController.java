package com.backend.hotelservice.controller;

import com.backend.hotelservice.entity.Property;
import com.backend.hotelservice.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @RolesAllowed({"partner"})
    @PostMapping("/add/{ownerId}")
    public ResponseEntity<Property> addNewProperty(@PathVariable ("ownerId")Long ownerId, @Valid @RequestBody Property property)
    {
        Property property1=propertyService.addNewProperty(ownerId, property);
        return ResponseEntity.ok(property1);
    }
    @RolesAllowed({"partner"})
    @GetMapping("/get/{propertyId}")
    public ResponseEntity<Property> getProperty(@PathVariable ("propertyId") UUID propertyId)
    {
        Property property=propertyService.getProperty(propertyId);
        return ResponseEntity.ok(property);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Property>> getAllProperty()
    {
        List<Property> property=propertyService.getAllProperty();
        return ResponseEntity.ok(property);
    }
}
