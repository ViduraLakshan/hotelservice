package com.backend.hotelservice.controller;

import com.backend.hotelservice.entity.User;
import com.backend.hotelservice.model.RegistrationCheck;
import com.backend.hotelservice.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<RegistrationCheck> checkUserRegistered(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws FirebaseAuthException {
        String token = auth.split(" ")[1].trim();
        RegistrationCheck registrationCheck = this.userService.checkUserRegistered(token);
        return ResponseEntity.ok(registrationCheck);
    }

    @PostMapping("/create")
    public UserRecord createUser(@Valid @RequestBody User createUser) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest();
        request.setEmail(createUser.getEmail()).setEmailVerified(true);
        request.setPhoneNumber(createUser.getPhoneNo())
                .setDisplayName(createUser.getUserName())
                .setPassword(createUser.getPassword());
        UserRecord userRecord = firebaseAuth.createUser(request);
        createUser.setUid(userRecord.getUid());
        User user=userService.createUser(createUser);
        return userRecord;
    }
}
