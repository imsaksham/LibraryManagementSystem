package com.intellewings.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intellewings.model.UserLoginDTO;
import com.intellewings.service.UserLoginService;

@RestController
@RequestMapping("/user")
public class UserLoginController {
	
	// {
	//  "username": "johndoe",
	//  "password": "john@123"
	//}

	@Autowired
	private UserLoginService userLoginService;
	
	// localhost:8088/user/login
	@PostMapping("/login")
	public ResponseEntity<String> userLogInToAccount(@RequestBody @Valid UserLoginDTO userLoginDTO) {
		String result = userLoginService.userLogIn(userLoginDTO);
		
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}
	
	// localhost:8088/user/login/logout?key=086c
	@PostMapping("/logout")
	public ResponseEntity<String> userLogOutFromAccount(@RequestParam String key) {
		String result = userLoginService.userLogOut(key);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
