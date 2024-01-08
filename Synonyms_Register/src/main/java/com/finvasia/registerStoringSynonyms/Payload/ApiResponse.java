package com.finvasia.registerStoringSynonyms.Payload;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	
	String message;
	List<String> errors;
	HttpStatus status;
	

}
