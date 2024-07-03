package com.microservices.accounts.controller;

import com.microservices.accounts.dto.CustomerDetailsConfig;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.service.CustomerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class CustomerController {

    CustomerDetailsService customerDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Operation(
            description = "fetch customer details operation"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status OK"
    )



    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsConfig> getCustomerDetails(
            @RequestHeader
                    ("correlation-id") String correlationId,
            @RequestParam @Pattern
                    (regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber)
    {
        logger.debug("test correlation id found: {}", correlationId);
        CustomerDetailsConfig result = customerDetailsService.fetchCustomerDetails(mobileNumber, correlationId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

}
