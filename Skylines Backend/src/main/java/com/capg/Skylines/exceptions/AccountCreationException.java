package com.capg.Skylines.exceptions;

public class AccountCreationException extends Exception{

	
	private static final long serialVersionUID = 1L;

	public AccountCreationException() {
		
		super();	
	}
	
	public AccountCreationException(final String message)
	{
		super(message);
	}
	
}
