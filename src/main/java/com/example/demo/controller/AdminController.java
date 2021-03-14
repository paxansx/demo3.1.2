package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {


    private final RoleService roleService;

    public AdminController( RoleService roleService) {

        this.roleService = roleService;

    }


    @GetMapping
    public String usersPage(Model model) {

        model.addAttribute("allRoles", roleService.getAllRole());
        return "users";
    }



}
