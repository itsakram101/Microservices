package com.microservices.accounts.service.clientFeign;

import com.microservices.accounts.dto.LoansConfigDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<LoansConfigDto> fetchLoan(String correlationId, String mobileNumber) {
        return null;
    }
}
