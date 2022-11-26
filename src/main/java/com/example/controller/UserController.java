package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/user/v1")
public class UserController {

	
	@Autowired
	UserService userService;
	
	
	@GetMapping(value = "/get")
	public ResponseEntity<User> getUser(@RequestParam("userId") String userId) throws Exception {
		
		if(userId == null) {
			throw new Exception("Invalid exception");
		}
		User user = userService.getUser(Long.parseLong(userId));
		return ResponseEntity.ok(user);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
		
		if(user == null) {
			throw new Exception("Invalid exception");
		}
		userService.saveUser(user);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getUsers() throws Exception {
		System.out.println("get users ********");
		return ResponseEntity.ok(userService.getUsers());
	}
}
