package com.microservices.accounts.controller;

import com.microservices.accounts.Constants.Constants;
import com.microservices.accounts.dto.AccountsConfigDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.dto.ErrorResponseDto;
import com.microservices.accounts.dto.ResponseDto;
import com.microservices.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(
        name = "Crud Operations for Accounts/Customer",
        description = "Use this REST Api to add/update/fetch/delete operations on the Accounts/Customer")
public class AccountsController {

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    private IAccountsService iAccountsService;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsConfigDto accountsConfigDto;

    @Operation(
            description = "Create an account operation"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status created"
    )
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @Operation(
            description = "fetch an account operation"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> getCustomerAccountInfo(@RequestParam @Pattern
            (regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                     String mobileNumber){

        CustomerDto result = iAccountsService.fetchCustomerAccountData(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @Operation(
            description = "Update an account operation"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomerInfo(@Valid @RequestBody CustomerDto customerDto){

        iAccountsService.updateCustomerData(customerDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(Constants.STATUS_200, Constants.MESSAGE_200));
    }

    @Operation(
            description = "Delete an account operation"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP status deleted"
                    ),
                    @ApiResponse(
                            responseCode = "417",
                            description = "HTTP status Expectation Failed, contact DEV TEAM",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )

                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP status INTERNAL SERVER ERROR",
                            content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
                    )
            }
    )

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomerInfo(@RequestParam @Pattern
            (regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                     String mobileNumber){

        boolean isItDeleted = iAccountsService.deleteCustomerData(mobileNumber  );

        if(isItDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200, Constants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(Constants.STATUS_417, Constants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Contact Info",
            description = "Contact Info details depending on ENV"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/accountsInfoENV")
    public ResponseEntity<AccountsConfigDto> getAccountsInfo(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsConfigDto);
    }

    @RateLimiter(name= "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Java 17");
    }

}
