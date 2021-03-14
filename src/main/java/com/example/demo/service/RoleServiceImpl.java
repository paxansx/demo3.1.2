package com.example.demo.service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.model.Role;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    final
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }


    @Override
    public Set<Role> getSetRole(Set<Role> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Object role: roles) {
            if (role.toString().equals("ADMIN")) {
                roleSet.add(roleRepository.findRoleByRole("ROLE_ADMIN"));
            }else {
                roleSet.add(roleRepository.findRoleByRole("ROLE_USER"));
            }
        }
        return roleSet;
    }
}
