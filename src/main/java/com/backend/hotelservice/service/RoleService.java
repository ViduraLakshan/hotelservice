package com.backend.hotelservice.service;

import com.backend.hotelservice.entity.Role;

public interface RoleService {
    Role createRole(Role role);

    Role getRoleByName(String partner);
}