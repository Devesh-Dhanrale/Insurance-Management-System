package com.cg.exceptions;

public class PolicyNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PolicyNotFoundException() {

	}

	public PolicyNotFoundException(String message) {
      super(message);
	}
}
