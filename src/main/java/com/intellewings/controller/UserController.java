package com.intellewings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intellewings.model.User;
import com.intellewings.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	// {
	//  "userId": 37,
	//  "firstName": "John",
	//  "lastName": "Doe",
	//  "username": "johndoe",
	//  "email": "johndoe@gmail.com",
	//  "mobileNumber": "7986178351",
	//  "password": "john@123",
	//  "books": [
	//      {
	//          "bookId": 19,
	//          "title": "Sample Book 1",
	//          "author": "Author 1",
	//          "category": "Fiction",
	//          "description": "Description 1"
	//      },
	//      {
	//          "bookId": 20,
	//          "title": "Sample Book 2",
	//          "author": "Author 2",
	//          "category": "Non-Fiction",
	//          "description": "Description 2"
	//      }
	//  ]
	//}

	@Autowired
	private UserService userService;
	
	// localhost:8088/user/register
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User registeredUser = userService.registerUser(user);
		
		return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
	}
	
	// localhost:8088/user/update?username=imsaksham9&key=53d6
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody User user, @RequestParam String username, @RequestParam String key) {
		String updatedUser = userService.updateUser(user, username, key);
		
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	// localhost:8088/user/delete?username=imsaksham9&key=53d6
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser(User user, @RequestParam String username, @RequestParam String key) {
		String deletedUser = userService.deleteUser(user, username, key);
		
		return new ResponseEntity<>(deletedUser, HttpStatus.OK);
	}
}
