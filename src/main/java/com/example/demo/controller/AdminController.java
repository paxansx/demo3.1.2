package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
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
	public String usersPage(Model model, @ModelAttribute("user") User user){
		model.addAttribute("users", service.getAllUser());
		model.addAttribute("newUser", new User());
		model.addAttribute("allRoles", roleService.getAllRole());
		return "users";
	}


	@PostMapping("/add")
	public String  usersAdd(@ModelAttribute("user") User user, @RequestParam("role") String[] roles) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(roleService.getSetRole(roles));
		service.addUser(user);
		return "redirect:/admin";
	}


	@PostMapping("/update")
	public String  update (@ModelAttribute("user") User user, @RequestParam("role") String[] roles) {
    	if (user.getPassword().equals("")){

			user.setPassword(service.getUserById(user.getId()).getPassword());

    		}if (!bCryptPasswordEncoder.matches(user.getPassword(), service.getUserById(user.getId()).getPassword())){

    			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			}
		user.setRoles(roleService.getSetRole(roles));
		service.updateUser(user);
		return "redirect:/admin";
	}

	@PostMapping("/remove")
	public String removeUser(@ModelAttribute("user") User user){
		service.removeUser(user.getId());
		return "redirect:/admin";
	}
	@GetMapping("/findOne")
	@ResponseBody
	public User findOne(Long id){

		return service.getUserById(id);
	}

}
