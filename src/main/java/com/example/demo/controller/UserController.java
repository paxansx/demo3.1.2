package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	private final UserService service ;

	public UserController(UserService service) {
		this.service = service;

	}

	@GetMapping
	public String getUserInfo(Model model1, Authentication authentication) {
		model1.addAttribute("user", service.loadUserByUsername(authentication.getName()));
		return "user";
	}



}