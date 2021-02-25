package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {
	final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService service ;
    private final RoleService roleService ;

    public AdminController(UserService service, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.service = service;
        this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}


	@GetMapping
	public String usersPage(Model model){
		model.addAttribute("users", service.getAllUser());
		return "users";
	}

	@GetMapping("/userAdd")
	public String  usersAdd(Model model, @ModelAttribute("user") User user){
		model.addAttribute("newUser", new User());
		model.addAttribute("allRoles", roleService.getAllRole());
		return "/userAdd";
	}

	@PostMapping("/add")
	public String  usersAdd(@ModelAttribute("user") User user, @RequestParam("role") String[] roles) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleService.getSetRole(roles));
		service.addUser(user);
		return "redirect:/admin";
	}


	@GetMapping("/userupdate/{id}")
	public String updateUser(Model model, @PathVariable("id") long id){
		model.addAttribute("allRoles", roleService.getAllRole());
		model.addAttribute("user", service.getUserById(id));
		return "/userUpdate";
	}
	@PostMapping("/update")
	public String  update (@ModelAttribute("user") User user, @RequestParam("role") String[] roles) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleService.getSetRole(roles));
		service.updateUser(user);
		return "redirect:/admin";
	}

	@PostMapping("/remove/{id}")
	public String removeUser(@PathVariable("id")long id){
		service.removeUser(id);
		return "redirect:/admin";
	}
}
