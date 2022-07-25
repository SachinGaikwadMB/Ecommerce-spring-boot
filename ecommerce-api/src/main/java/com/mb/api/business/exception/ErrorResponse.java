package com.mb.api.business.exception;

import java.util.Date;

public class ErrorResponse
{
	private Date timeStamp;
	private String message;
	private int statusCode;
	
	public ErrorResponse(Date timeStamp, String message, int statusCode)
	{
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.statusCode = statusCode;
	}

	public Date getTimeStamp()
	{
		return timeStamp;
	}

	public String getMessage()
	{
		return message;
	}

	public int getStatusCode()
	{
		return statusCode;
	}
	
	
}
