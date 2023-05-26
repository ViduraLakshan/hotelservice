package com.backend.hotelservice.controller;

import com.backend.hotelservice.entity.Role;
import com.backend.hotelservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role)
    {
        Role roleFromDb=roleService.createRole(role);
        return ResponseEntity.ok(roleFromDb);
    }
}