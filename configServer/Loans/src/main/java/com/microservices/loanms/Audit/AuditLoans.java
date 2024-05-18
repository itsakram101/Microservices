package com.microservices.loanms.Audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditLoans")
public class AuditLoans implements AuditorAware {

    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("LOAN_MS");
    }
}
