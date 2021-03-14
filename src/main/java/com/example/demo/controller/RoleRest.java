package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleRest {

    private final RoleService roleService;

    public RoleRest(RoleService roleService) {

        this.roleService = roleService;

    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRole();
    }
}
