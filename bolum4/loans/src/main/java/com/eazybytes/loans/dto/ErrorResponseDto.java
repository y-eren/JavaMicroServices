package com.eazybytes.loans.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class ErrorResponseDto {

	
	
	private String errrorMessage;
	
	private String apiPath;
	
	private HttpStatus statusCode;
	
	private LocalDateTime errorTime;
	
	
}
