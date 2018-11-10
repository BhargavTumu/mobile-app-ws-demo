package com.btumu.app.ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
	
	@GetMapping()
	public String getUser() {
		return "get users method was called !!";
	}
	
	@PostMapping
	public String createUser() {
		return "create user method was called.";
	}
	
	@PutMapping
	public String updateUser() {
		return "update user method was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user method was called";
	}
}
