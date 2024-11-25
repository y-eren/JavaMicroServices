package com.eazybytes.loans.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eazybytes.loans.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	   @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
	                                                                  WebRequest webRequest) {
	        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
	                webRequest.getDescription(false),
	                exception.getMessage(),
	                HttpStatus.INTERNAL_SERVER_ERROR,
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	@ExceptionHandler(LoanAlreadyExistException.class)
	public ResponseEntity<ErrorResponseDto> handleLoanAlreadyExistsException(LoanAlreadyExistException exc, WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), exc.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exc, WebRequest webRequest){
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false), exc.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	}
	
}
