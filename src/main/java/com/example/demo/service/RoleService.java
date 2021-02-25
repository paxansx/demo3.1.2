package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public List<Role> getAllRole();
    public Set<Role> getSetRole(String[] roles);
}
