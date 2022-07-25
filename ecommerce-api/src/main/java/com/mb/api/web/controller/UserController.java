package com.mb.api.web.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.api.business.service.UserService;
import com.mb.api.web.model.UpdateUserModel;

@RestController
@RequestMapping("/api/users")
public class UserController
{	
	@Autowired
	private UserService userService;
	
	@PostMapping("/update")
	public ResponseEntity<String> updateUser(@Valid @RequestBody UpdateUserModel userModel) {
		String responseMessage = userService.updateUser(userModel);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}
}
