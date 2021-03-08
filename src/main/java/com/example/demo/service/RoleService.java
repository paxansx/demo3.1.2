package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRole();

    Set<Role> getSetRole(String[] roles);
}
