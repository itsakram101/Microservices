package com.microservices.accounts.service.clientFeign;

import com.microservices.accounts.dto.LoansConfigDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans", fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping("/api/get")
    public ResponseEntity<LoansConfigDto> fetchLoan(@RequestHeader("correlation-id") String correlationId,
                                                    @RequestParam String mobileNumber);
}
