package com.mb.api.web.model;

import javax.validation.constraints.NotBlank;

public class UpdateUserModel
{
	@NotBlank
	private String email;
	
	@NotBlank
	private String updatedEmail;
	
	public UpdateUserModel() {}

	public UpdateUserModel(String email, String updatedEmail)
	{
		super();
		this.email = email;
		this.updatedEmail = updatedEmail;
	}

	public String getEmail()
	{
		return email;
	}

	public String getUpdatedEmail()
	{
		return updatedEmail;
	}
	
	
}
