package com.microservices.accounts.controller;

import com.microservices.accounts.dto.CustomerDetailsConfig;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.service.CustomerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class CustomerController {

    CustomerDetailsService customerDetailsService;

    @Operation(
            description = "fetch customer details operation"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsConfig> getCustomerDetails(@RequestParam @Pattern
            (regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                                      String mobileNumber){

        CustomerDetailsConfig result = customerDetailsService.fetchCustomerDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

}
