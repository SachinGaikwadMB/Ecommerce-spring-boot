package com.mb.api.web.controller;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.api.business.service.UserService;
import com.mb.api.web.model.LoginModel;
import com.mb.api.web.model.RegisterModel;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private UserService userService;
	
	
	
	//Only for testing
	@GetMapping("/test")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String test() {
		return "test work";
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/user")
	public String user() {
		return "user url";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin() {
		return "admin url";
	}
	
	//Method to Register new user 
	@PostMapping("/user/new")
	public ResponseEntity<String> registerNewUser(@Valid @RequestBody RegisterModel registerModel) {
		 String responseMessage = userService.registerNewUser(registerModel);
		 return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}
	
	
	//method to Register new Admin
	@PostMapping("/admin/new")
	public ResponseEntity<String> registeNewAdmin(@Valid @RequestBody RegisterModel registerModel) {
		String responseMessage = userService.registerNewAdmin(registerModel);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}
	
	
	//Method for login
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginModel loginModel) {
		Map<String, Object> responsebody = userService.login(loginModel);
		return new ResponseEntity<>(responsebody, HttpStatus.OK);
	}
	
	
}
