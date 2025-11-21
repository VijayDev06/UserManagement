package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "USER_MANAGEMENT", description = "User Related Operation ")
public class UserController {

	@Autowired
	UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@PostMapping("/reqUser")
	public ResponseEntity<?> registerUser(@RequestBody User u) {

		User user = userService.registerUserInService(u);

		logger.info("User Added");

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@GetMapping("/getUser")
	public ResponseEntity<?> getUser() {

		List<User> user = userService.GetUserInService();

		logger.info("User Data Fetched");

		if (user != null && !user.isEmpty()) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int id) {

		User user = userService.GetUserByIdInService(id);

		logger.info("User Data Updated for id :" + id);

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteUserById/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") int id) {

		User user = userService.deleteUserByIdInService(id);

		logger.info("User Deleted for id :" + id);

		if (user != null) {
			return new ResponseEntity<>("user deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteUserByIds")
	public ResponseEntity<?> deleteUserByIds(@RequestBody List<Integer> userIds) {

		userService.deleteUserByIdsInService(userIds);

		if (userIds != null) {
			logger.info("User Deleted for id :" + userIds);
			return new ResponseEntity<>("user deleted for id :" + " " + userIds, HttpStatus.OK);
		} else {
			logger.error("User not found for id :" + userIds);
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteAllUser")
	public ResponseEntity<?> deleteAllUser() {

		userService.deleteAllUser();

		logger.info("All Users Deleted");

		return new ResponseEntity<>("All user deleted", HttpStatus.OK);
	}

	@Operation(summary = "Update user Details", description = "It will update user based on givrn user id.")
	@PutMapping("/updateUserById/{userId}")
	public ResponseEntity<?> updateUserById(@PathVariable("userId") int id, @RequestBody User u) {

		User user = userService.updateUserByIdInService(id, u);

		logger.info("User Data Updated for id :" + id + "" + user);

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getpages")
	public List<User> getUsersByPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size) {
		
		return userService.getUsersByPage(page, size);
	}
	
	@GetMapping("/allPages")
	public List<User> getAllUserinpages(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size) {
		
		return userService.getAllUserinpages(page, size);
	}
	
	
	
	
	


}
