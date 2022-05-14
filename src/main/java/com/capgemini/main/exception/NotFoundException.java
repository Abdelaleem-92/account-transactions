package com.capgemini.main.exception;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8367968550868772649L;

	private final int statusCode;

	public NotFoundException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
