package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRest {

    private final UserService service;
    private final RoleService roleService;

    public AdminRest(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;

    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        return service.getUserById(id);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        user.setRoles(roleService.getSetRole(user.getRoles())); //это тут потому что криво передаю роль с клиента
        service.addUser(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        user.setRoles(roleService.getSetRole(user.getRoles()));//это тут потому что криво передаю роль с клиента
        service.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        service.removeUser(id);
    }




}
