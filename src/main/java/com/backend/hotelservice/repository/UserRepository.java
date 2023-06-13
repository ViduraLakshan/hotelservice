package com.backend.hotelservice.repository;

import com.backend.hotelservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
    Optional<User>findByUid(String uid);
}

