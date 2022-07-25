package com.mb.api.business.exception;

import com.mb.api.enums.ErrorCode;

public class CustomException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;
	
	public CustomException(String message ,ErrorCode errorCode)
	{
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode()
	{
		return errorCode;
	}


	
	
}
