package com.capgemini.main.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.main.exception.NotFoundException;
import com.capgemini.main.exception.ProblemFormat;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemFormat> handleException(Exception ex){
		ProblemFormat problem = new ProblemFormat();
		problem.setTitle("Generic Exception.");
		problem.setMessage(ex.getMessage());
		problem.setStausCode(-1);
		if(ex instanceof NotFoundException) {
			problem.setStausCode(((NotFoundException)ex).getStatusCode());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(problem);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ProblemFormat> handleBadCredentialsException(Exception ex){
		ProblemFormat problem = new ProblemFormat();
		problem.setTitle("BAD CREDENTIALS.");
		problem.setMessage(ex.getMessage());
		problem.setStausCode(HttpStatus.UNAUTHORIZED.value());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(problem);
	}
}
