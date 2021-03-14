package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserRest {
    private final UserService service;


    public UserRest(UserService service) {
        this.service = service;


    }

    @GetMapping("/user")
    public User userData(Principal principal) {
        return (User) service.loadUserByUsername(principal.getName());
    }
}
