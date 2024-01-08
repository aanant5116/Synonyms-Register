package com.finvasia.registerStoringSynonyms.Exception;

import java.net.http.HttpHeaders;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.finvasia.registerStoringSynonyms.Payload.ApiResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/*
	 * @ExceptionHandler(WordNotFoundException.class) public
	 * ResponseEntity<ApiResponse> handleWordNotFoundException(WordNotFoundException
	 * ex) { ApiResponse apiResponse = new
	 * ApiResponse(ex.getMessage(),Collections.emptyList(), HttpStatus.BAD_REQUEST);
	 * return new ResponseEntity<>(apiResponse,new
	 * HttpHeaders(null),apiResponse.getStatus()); }
	 */
	
	@ExceptionHandler(WordNotFoundException.class)
	public ResponseEntity<ApiResponse> handleWordNotFoundException(WordNotFoundException ex) {
	    ApiResponse apiResponse = new ApiResponse(ex.getMessage(), Collections.emptyList(), HttpStatus.BAD_REQUEST);
	    return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	
	
	
	
	
	@ExceptionHandler(UnsupportedOperationException.class)
	public ResponseEntity<ApiResponse> handleUnsupportedOperationOperationException(UnsupportedOperationException ex)
	{
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(), Collections.emptyList(), HttpStatus.BAD_REQUEST);
		//return new ResponseEntity<>(apiResponse, new HttpHeaders(),apiResponse.getStatus());
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}
