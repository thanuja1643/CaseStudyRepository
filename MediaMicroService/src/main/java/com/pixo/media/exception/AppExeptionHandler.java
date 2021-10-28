package com.pixo.media.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExeptionHandler {
	@ExceptionHandler(FileStorageException.class)
	public ResponseEntity<Object> handleException(FileStorageException ex) {
		 //LOGGER.error("Invalid Input Exception: ",ex.getMessage());
		 return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);


	}

}
