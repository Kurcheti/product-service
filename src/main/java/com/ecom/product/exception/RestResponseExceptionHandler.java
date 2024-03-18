package com.ecom.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceException productServiceException){
		                     ErrorResponse errorResponse = ErrorResponse.builder()
		                    		                       .message(productServiceException.getMessage())
		                    		                       .errorCode(productServiceException.getErrorCode())
		                    		                       .build();
		    return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
