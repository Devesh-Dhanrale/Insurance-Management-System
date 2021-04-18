package com.cg.exceptions;

public class AgentNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgentNotFoundException() {

	}

	public AgentNotFoundException(String message) {
      super(message);
	}
}
