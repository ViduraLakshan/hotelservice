package com.backend.hotelservice.configuration;

import com.backend.hotelservice.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class FireBaseTokenFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(FireBaseTokenFilter.class);
    @Autowired
    private FirebaseService firebaseService;
    public FirebaseAuth firebaseAuth;

    public FireBaseTokenFilter(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authenticationHeader = request.getHeader("Authorization");

        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer "))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Missing token!");


        String token = authenticationHeader.split(" ")[1].trim();
        FirebaseToken decodedToken=null;
        try {
            decodedToken = firebaseAuth.verifyIdToken(token);
            String uId=decodedToken.getUid();
            //List<SimpleGrantedAuthority> authorities = firebaseService.getAuthorities(uId);

            UserNamePasswordAuthentication userNamePasswordAuthentication = new UserNamePasswordAuthentication(
                    uId,
                    null,
                    null
            );
            SecurityContextHolder.getContext()
                    .setAuthentication(userNamePasswordAuthentication);

        }
        catch (FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Error! "+e.toString());
        }

        //if token is invalid
        if (decodedToken==null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid token!");
        }

        chain.doFilter(request,response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return request.getServletPath().equals("/api/v1/auth/create");
    }

}