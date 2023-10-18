package com.mb.api.enums;

public enum ErrorCode {

	//Built in Error Code
	INTERNAL_SERVER_ERROR(500),
	BAD_REQUEST(400),
	NOT_FOUND(404),
	UNAUTHORIZED(401),
	
	/*
	 * Here we have defined error specific code  
	 * 604/605 is for the user/admin not found 
	 * 609/610 is for the user/admin already exist
	 * 
	 * * */
	USER_NOT_FOUND(604),
	ADMIN_NOT_FOUND(605),

	USER_ALREADY_EXITS(609),
	ADMIN_ALREADY_EXITS(610),
	PRODUCT_ALREADY_EXISTS(611),
	INVALID_CREDENTIAL(612),
	INVALID_DATA(613);
	
	
	
	private final int code;
	
	private ErrorCode(int code) {
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}
	
}
