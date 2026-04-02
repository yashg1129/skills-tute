package com.skills.tute.exception;

public class DuplicateResourceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public DuplicateResourceException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
