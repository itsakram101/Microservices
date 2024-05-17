package com.microservices.loanms.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String inputName, String inputValue){

        super(String.format("%s not found with the input %s : '%s'",resourceName, inputName, inputValue));
    }
}
