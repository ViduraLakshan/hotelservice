package com.backend.hotelservice.service.impl;

import com.backend.hotelservice.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class FirebaseServiceImpl implements FirebaseService {


    private final FirebaseAuth firebaseAuth;

    public FirebaseServiceImpl() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public String verifyIdToken(String token) throws FirebaseAuthException {
        FirebaseToken decodedToken = firebaseAuth.verifyIdToken(token);
        return decodedToken.getUid();
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities(String fbId) throws FirebaseAuthException {
        Set<String> userRoles = getUserRoles(fbId);
        return userRoles.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public Set<String> getUserRoles(String fbId) throws FirebaseAuthException {
        UserRecord user = firebaseAuth.getUser(fbId);
        Map<String, Object> customClaims = user.getCustomClaims();
        Set<String> roles = new HashSet<>();
        for (var entry : customClaims.entrySet()) {
            if (entry.getValue() instanceof Boolean && (boolean) entry.getValue()) {
                roles.add(entry.getKey());
            }
        }
        return roles;
    }

    @Override
    public void setUserRole(String fbId, String role) throws FirebaseAuthException {
        UserRecord user = firebaseAuth.getUser(fbId);
        Map<String, Object> currentClaims = user.getCustomClaims();
        if (currentClaims.isEmpty()) {
            Map<String, Object> claims = new HashMap<>();
            claims.put(role, true);

            firebaseAuth.setCustomUserClaims(fbId, claims);
        } else {
            if (!currentClaims.containsKey(role)) {
                currentClaims.put(role, true);

                firebaseAuth.setCustomUserClaims(fbId, currentClaims);
            } else {

            }

        }


    }

//    @Override
//    public UserRecord createUser(@NotNull UserProfile userProfile) throws FirebaseAuthException {
//        UserRecord.CreateRequest request = new UserRecord.CreateRequest();
//        DTUser user = userProfile.getUser();
//        request.setEmail(user.getEmail()).setEmailVerified(true);
//        request.setPhoneNumber(userProfile.getPhoneNo())
//                .setDisplayName(userProfile.getUser().getUserName())
//                .setDisabled(false);
//
//        if (!userProfile.getProfileImg().isEmpty()) request.setPhotoUrl(userProfile.getProfileImg());
//
//        return firebaseAuth.createUser(request);
//    }

//    @Override
//    public void saveUserRoles(Set<UserRole> roles,String fbId) throws FirebaseAuthException {
//        Map<String, Object> claims = new HashMap<>();
//        for (UserRole role : roles) {
//            claims.put(role.getName(), true);
//        }
//        FirebaseAuth.getInstance().setCustomUserClaims(fbId, claims);
//    }

}

