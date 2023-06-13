package com.backend.hotelservice.service.impl;

import com.backend.hotelservice.entity.User;
import com.backend.hotelservice.model.RegistrationCheck;
import com.backend.hotelservice.repository.UserRepository;
import com.backend.hotelservice.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public RegistrationCheck checkUserRegistered(String token) throws FirebaseAuthException {
        FirebaseToken decodedToken= FirebaseAuth.getInstance().verifyIdToken(token);
        String uId=decodedToken.getUid();
        Optional<User> optionalUser=userRepository.findByUid(uId);
        RegistrationCheck registrationCheck=new RegistrationCheck();
        if (optionalUser.isPresent())
        {
            registrationCheck.setRegistered(true);
            return registrationCheck;
        }
        registrationCheck.setRegistered(false);
        return registrationCheck;
    }
}
