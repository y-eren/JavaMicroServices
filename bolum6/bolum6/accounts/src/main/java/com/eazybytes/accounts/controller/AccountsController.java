package com.eazybytes.accounts.controller;



import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

	private final IAccountsService iAccountsService;

	public AccountsController(IAccountsService iAccountsService) {
		this.iAccountsService = iAccountsService;
	}

	@Value("${build.version}")
	private String buildVersion;

	@Autowired
	private Environment environment;

	@Autowired
	private AccountsContactInfoDto accountsContactInfoDto;
	
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

		@Operation(
				summary="Get build information",
				description = "Rest Api to fetch Customer & Account details based on a mobile number"
		)
		@ApiResponses({
				@ApiResponse(
						responseCode="200",
						description = "HTTP Status OK"
				),
				@ApiResponse(
						responseCode = "500",
						description = "HTTP Status Internal Server Error",
						content = @Content(
								schema = @Schema(implementation = ErrorResponseDto.class)
						)
				)
		})
		@GetMapping("/build-info")
	public ResponseEntity<String> getBuildInfo() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(buildVersion);
		}

	@Operation(
			summary="Get java version information",
			description = "Rest Api to fetch Customer & Account details based on a mobile number"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode="200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
					)
			)
	})



	@GetMapping("/java-version")
	public ResponseEntity<String> getJavaVersion() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(environment.getProperty("JAVA_HOME"));
	}

	@Operation(
			summary="Get contact information",
			description = "Rest Api to fetch Customer & Account details based on a mobile number"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode="200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
					)
			)
	})



	@GetMapping("/contact-info")
	public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(accountsContactInfoDto);
	}




}
