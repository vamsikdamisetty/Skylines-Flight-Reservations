package com.capg.Skylines.exceptions;

public class NoBookingsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBookingsException() {
		super();
	}
	
	public NoBookingsException(final String msg)
	{
		super(msg);
	}
}
