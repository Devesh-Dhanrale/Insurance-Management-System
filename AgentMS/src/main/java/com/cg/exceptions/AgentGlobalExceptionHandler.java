package com.cg.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AgentGlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(AgentGlobalExceptionHandler.class);
	
	@ExceptionHandler(AgentNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleAgentNotFoundException(AgentNotFoundException e,HttpServletRequest request) {
		
		logger.info("In controller advice to handle agent not found exception");
		
		ErrorInfo error = new ErrorInfo();
		
		error.setUrl(request.getRequestURI());
		error.setMessage(e.getMessage());
		
		return error;
	}
	
}
