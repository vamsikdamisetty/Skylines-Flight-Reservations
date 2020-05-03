package com.capg.Skylines.exceptions;

public class FlightNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlightNotFoundException() {
		super();
	}
	
	public FlightNotFoundException(final String msg) {
		super(msg);
	}
	
}
