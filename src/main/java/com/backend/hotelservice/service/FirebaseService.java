package com.backend.hotelservice.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;


public interface FirebaseService   {

    String verifyIdToken(String token) throws FirebaseAuthException;

    List<SimpleGrantedAuthority> getAuthorities(String fbId) throws FirebaseAuthException;

    Set<String> getUserRoles(String fbId) throws FirebaseAuthException;

    void setUserRole(String fbId,String role) throws FirebaseAuthException;

    //UserRecord createUser(UserProfile userProfile) throws FirebaseAuthException;

    //void saveUserRoles(Set<UserRole> roles,String fbId) throws FirebaseAuthException;

}
