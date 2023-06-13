package com.backend.hotelservice.service;

import com.backend.hotelservice.entity.User;
import com.backend.hotelservice.model.RegistrationCheck;

import com.google.firebase.auth.FirebaseAuthException;

public interface UserService {
    User createUser(User user);

    RegistrationCheck checkUserRegistered(String token) throws FirebaseAuthException;
}
