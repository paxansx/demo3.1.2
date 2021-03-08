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

    private final UserService service;
    private final RoleService roleService;

    public AdminController(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;

    }


    @GetMapping
    public String usersPage(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("users", service.getAllUser());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getAllRole());
        return "users";
    }


    @PostMapping("/add")
    public String usersAdd(@ModelAttribute("user") User user, @RequestParam("role") String[] roles) {
        user.setRoles(roleService.getSetRole(roles));
        service.addUser(user);
        return "redirect:/admin";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("role") String[] roles) {
        user.setRoles(roleService.getSetRole(roles));
        service.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/remove")
    public String removeUser(@ModelAttribute("user") User user) {
        service.removeUser(user.getId());
        return "redirect:/admin";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public User findOne(Long id) {
        return service.getUserById(id);
    }

}
