package com.microservices.accounts.service.clientFeign;

import com.microservices.accounts.dto.CardsConfigDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient   {

    @GetMapping("/api/get")
    public ResponseEntity<CardsConfigDto> fetchCard(@RequestHeader("correlation-id") String correlationId,
                                                    @RequestParam String mobileNumber);
}
