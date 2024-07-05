package com.microservices.accounts.service.clientFeign;

import com.microservices.accounts.dto.CardsConfigDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements  CardsFeignClient{
    @Override
    public ResponseEntity<CardsConfigDto> fetchCard(String correlationId, String mobileNumber) {
        return null;
    }
}
