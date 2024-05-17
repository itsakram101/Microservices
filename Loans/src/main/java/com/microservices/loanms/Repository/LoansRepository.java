package com.microservices.loanms.Repository;

import com.microservices.loanms.Dto.LoansDto;
import com.microservices.loanms.Entity.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<LoansEntity, Long> {

    Optional<LoansEntity> findByMobileNumber(String mobileNumber);
}
