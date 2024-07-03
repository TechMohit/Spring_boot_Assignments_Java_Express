package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.User;
import com.javaexpress.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public void createUser(@RequestBody User user) {
		log.info("Usercontroller ::createUser {} {}", user.getEmail(), user.getUsername());
		userService.createUser(user);

	}

	// http://localhost:8080/api/v1/user/123
	@GetMapping("/test/{userId}")
	public User getUserById(@PathVariable Long userId) {
		log.info("Usercontroller:: getUserById {}", userId);
		return userService.findUserByID(userId);

	}

	@PutMapping("{userId}")
	public void updateUser(@PathVariable Long userId, @RequestBody User user) {
		log.info("Usercontroller:: updateUser {} {}", userId, user.getEmail());
		userService.updateUser(userId, user);
	}

	@PatchMapping("changepwd/{userId}")
	public void updatePassword(@PathVariable Long userId, @RequestBody User user) {
		log.info("Usercontroller:: updatePassword {} {}", userId, user.getPassword());
		userService.updatePassword(userId, user);
	}

	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable Long userId) {
		log.info("Usercontroller:: deleteUser {}", userId);
		userService.deleteUser(userId);

	}

	@GetMapping("/getUserName/{userName}")
	public User fetchUserByUserName(@PathVariable String userName) {
		log.info("userController:: fetchUserByUserName {}", userName);
		return userService.getUserDetailsByUserName(userName);
	}

	@GetMapping("/getEmail/{email}")
	public User fetchUserByEmail(@PathVariable String email) {
		log.info("userController:: fetchUserByEmail {}", email);
		return userService.getUserDetailsByEmail(email);
	}

	@GetMapping("/getEmailOrUserName")
	public User fetchUserByEmailOrUserName(@RequestParam(required = false) String username,
			@RequestParam(required = false) String email) {
		log.info("userController:: fetchUserByEmail {}", username);
		return userService.getUserDetailsEmailOrUserName(username, email);
	}
}
