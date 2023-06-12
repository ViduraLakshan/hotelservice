package com.backend.hotelservice.controller;

import com.backend.hotelservice.entity.PropertyOwner;
import com.backend.hotelservice.entity.Role;
import com.backend.hotelservice.service.PropertyOwnerService;
import com.backend.hotelservice.service.RoleService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/propertyOwner/auth")
public class PropertyOwnerController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private FirebaseAuth firebaseAuth;
    @Autowired
    private PropertyOwnerService propertyOwnerService;
    @PostMapping("/add")
    // @RolesAllowed({"partner","user"})
    public ResponseEntity<PropertyOwner> addNewOwner(@RequestBody PropertyOwner propertyOwner) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest();
        request.setEmail(propertyOwner.getEmail()).setEmailVerified(true);
        request.setPhoneNumber(propertyOwner.getPhoneNo())
                .setDisplayName(propertyOwner.getUserName())
                .setPassword(propertyOwner.getPassword());
        UserRecord userRecord = firebaseAuth.createUser(request);
        propertyOwner.setUid(userRecord.getUid());
        Map<String, Object> claims = new HashMap<>();
        claims.put("partner", true);
        FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
        Role role=roleService.getRoleByName("partner");
        Set<Role> roles=propertyOwner.getRoles();
        roles.add(role);
        propertyOwner.setRoles(roles);
        PropertyOwner owner=propertyOwnerService.addNewOwner(propertyOwner);
        return ResponseEntity.ok(owner);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Page<PropertyOwner>>getAll( @RequestParam (value = "page") int page,
                                                      @RequestParam(value = "size") int size)
    {
        Page<PropertyOwner> propertyOwners=propertyOwnerService.getAll(page,size);
        return ResponseEntity.ok(propertyOwners);
    }
}
