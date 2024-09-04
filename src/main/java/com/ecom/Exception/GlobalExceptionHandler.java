package com.ecom.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ecom.Entities.GenericResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataValidationException.class)
	public ResponseEntity<GenericResponseEntity> handleDataValidationException(DataValidationException exception,
			WebRequest request)  {
		
		//ErrorMessage message = new ErrorMessage(412, exception.getMessage());
		
		GenericResponseEntity message = new GenericResponseEntity(412, exception.getMessage());
		
		return new  ResponseEntity<GenericResponseEntity>(message,HttpStatus.PRECONDITION_FAILED);
	}
}