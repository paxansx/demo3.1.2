package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControllerAdmin {

    private final UserService service;
    private final RoleService roleService;

    public RestControllerAdmin(UserService service, RoleService roleService) {
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
        user.setRoles(roleService.getSetRole(user.getRoles()));
        service.addUser(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        user.setRoles(roleService.getSetRole(user.getRoles()));
        service.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        service.removeUser(id);
    }
    @GetMapping("/user")
    public User userData(Principal principal) {
        return (User) service.loadUserByUsername(principal.getName());
    }

}
