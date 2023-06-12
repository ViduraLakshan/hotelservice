package com.backend.hotelservice.repository;

import com.backend.hotelservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role getRoleByName(String role);
}
