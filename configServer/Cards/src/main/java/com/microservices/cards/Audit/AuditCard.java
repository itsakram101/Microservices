package com.microservices.cards.Audit;

import com.microservices.cards.entity.BaseEntity;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditCard")
public class AuditCard implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("CARD_MS");
    }
}
