package com.mb.api.business.exception;

import java.util.Date;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalExceptionHandler
{
	@ExceptionHandler(value = {CustomException.class})
	public ErrorResponse handleAlreadyException(CustomException ex) {
		return new ErrorResponse(new Date(), ex.getMessage(), ex.getErrorCode().getCode());
	}
}
