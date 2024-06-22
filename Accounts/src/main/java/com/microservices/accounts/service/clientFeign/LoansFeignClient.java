package com.microservices.accounts.service.clientFeign;

import com.microservices.accounts.dto.LoansConfigDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping("/api/get")
    public ResponseEntity<LoansConfigDto> fetchLoan(@RequestParam String mobileNumber);
}
