package com.san.spring.demo.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.san.spring.demo.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ErrorResponse> respondForNotValid(MethodArgumentNotValidException e) {
		
		List<FieldError> errors = e.getBindingResult().getFieldErrors();
		ErrorResponse errorResponse = new ErrorResponse();
		
		errors.forEach(error -> {
			errorResponse.getErrors().put(error.getField(), error.getDefaultMessage());
		});
		
		errorResponse.setStatus(400);
		errorResponse.setMessage("Validation Failed");
		errorResponse.setTimestamp(LocalDateTime.now());

//		return ResponseEntity.status(400).body(errorResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(TaskNotFoundException.class)
	ResponseEntity<String> respondForTaskNotFound(TaskNotFoundException e) {	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class) 
	ResponseEntity<String> respondIfUserAlreadyExists(UserAlreadyExistsException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException e) {
	    ErrorResponse error = new ErrorResponse();
	    error.setStatus(401);
	    error.setMessage(e.getMessage());
	    error.setTimestamp(LocalDateTime.now());
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
}
