package com.microservices.loanms;

import com.microservices.loanms.Dto.LoansConfigDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditLoans")
@EnableConfigurationProperties(value = {LoansConfigDto.class})

public class LoanMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanMsApplication.class, args);
    }

}
