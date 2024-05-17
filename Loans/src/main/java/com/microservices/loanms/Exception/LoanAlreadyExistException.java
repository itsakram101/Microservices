package com.microservices.loanms.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistException extends RuntimeException{

    public LoanAlreadyExistException(String message){
        super(message);
    }
}
