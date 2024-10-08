package com.eazybytes.accounts.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
// BİRDEN FAZLA API RESPONSLARI VAR İSE APIRESPONSES KULLANABİLİRSİN {} İÇERİSİNDE
@Tag(
	name = "CRUD REST APIs Accountslar için",
	description = "accountslar için create, update, fetch ve delete işlemleri")
@RestController
@RequestMapping(path="/api", produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {
// responseentity sayesinde sadece body yerine birçok mesaj gönderebiliyoruz
	@Autowired
	private IAccountsService iAccountsService;
	
	@Operation(
			summary = "Rest api için hesap oluşturur",
			description = "create account için hesap oluşturur. ")
	@ApiResponse(
			responseCode = "201",
			description = "HTTP STATUS CREATED")
	@PostMapping("/create")
	public ResponseEntity<ResponseDto>createAccount(@Valid @RequestBody CustomerDto customerDto)
{
		iAccountsService.createAccount(customerDto);
	return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
}
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto>fetchAccountDetails(@RequestParam
			 @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	String mobileNumber) {
	CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
	return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}
	
	@PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }
	  @DeleteMapping("/delete")
	    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
	                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                                String mobileNumber) {
	        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
	        if(isDeleted) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
	        }
	    }

	
}
