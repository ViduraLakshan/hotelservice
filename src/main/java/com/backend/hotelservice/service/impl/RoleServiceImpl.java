package com.backend.hotelservice.service.impl;

import com.backend.hotelservice.entity.Role;
import com.backend.hotelservice.repository.RoleRepository;
import com.backend.hotelservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String role) {
        return roleRepository.getRoleByName(role);
    }
}
