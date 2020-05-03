package com.capg.Skylines.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UnableToBookException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleMobileNumberRegistered(final UnableToBookException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}
	
	@ExceptionHandler(AccountCreationException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleAccountCreation(final AccountCreationException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}
	
	@ExceptionHandler(AccountValidationException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleAccountValidation(final AccountValidationException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleUserNotFound(final UserNotFoundException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}
	
	@ExceptionHandler(NoBookingsException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleNoBooking(final NoBookingsException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}
	
	@ExceptionHandler(FlightNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleFlightNotFound(final FlightNotFoundException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse(exception.getLocalizedMessage());
		return error;
	}
	
}
