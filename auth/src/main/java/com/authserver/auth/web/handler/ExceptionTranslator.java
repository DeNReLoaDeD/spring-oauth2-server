package com.authserver.auth.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.authserver.auth.web.dto.RestError;
import com.authserver.auth.web.exception.UsernameAlreadyExistsException;


@ControllerAdvice
public class ExceptionTranslator {


	/**
	 * HelloServiceDummyException exception handler
	 * 
	 * @param exception
	 *            to process
	 * @return {@link RestError} instance
	 */
	@ExceptionHandler(UsernameAlreadyExistsException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestError processHelloServiceDummyException(UsernameAlreadyExistsException exception) {
		return new RestError("user_already_exists", exception.getMessage(),
				HttpStatus.BAD_REQUEST.value());
	}
	
}