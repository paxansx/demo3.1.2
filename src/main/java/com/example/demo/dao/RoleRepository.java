package com.example.demo.dao;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRole(String roles);


}
