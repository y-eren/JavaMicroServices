package com.eazybytes.loans.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.loans.constants.LoansConstants;
import com.eazybytes.loans.dto.LoansDto;
import com.eazybytes.loans.dto.ResponseDto;
import com.eazybytes.loans.service.ILoansService;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

	@Autowired
	private ILoansService iLoansService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createLoan(@RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
		iLoansService.createLoans(mobileNumber);
		return ResponseEntity
		.status(HttpStatus.CREATED)
		.body(new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<LoansDto> fetchLoans(@RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
		LoansDto loansDto = iLoansService.fetchLoans(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(loansDto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateLoans(@RequestBody LoansDto loansDto) {
		boolean isUpdated = iLoansService.updateLoan(loansDto);
		if(isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
		}
		else {
			  return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
		}
	}
	
	 @DeleteMapping("/delete")
	    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam
	                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                                String mobileNumber) {
	        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
	        if(isDeleted) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
	        }
	    }
		
	
	
	
	
	
	
}
