package com.mb.api.web.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterModel {
	
	@NotBlank(message = "This field should not be empty!")
	@Email
	private String email;
	
	@NotBlank(message = "This field should not be empty!")
	@Size(min = 2, max = 25)
	private String username;
	
	@NotBlank(message = "This field should not be empty!")
	@Size(min = 8, max = 20)
	private String password;

	//only getters
	public String getEmail()
	{
		return email;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}
	

}
