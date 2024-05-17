package com.microservices.loanms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditLoans")
public class LoanMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanMsApplication.class, args);
    }

}
