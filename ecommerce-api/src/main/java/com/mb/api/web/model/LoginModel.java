package com.mb.api.web.model;

import javax.validation.constraints.NotBlank;

public class LoginModel
{
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}
	
	
}
