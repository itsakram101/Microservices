package com.microservices.loanms.Service;

import com.microservices.loanms.Dto.LoansDto;
import com.microservices.loanms.Entity.LoansEntity;
import com.microservices.loanms.Exception.LoanAlreadyExistException;
import com.microservices.loanms.Exception.ResourceNotFoundException;
import com.microservices.loanms.LoansConstants.LoansConstants;
import com.microservices.loanms.Repository.LoansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static com.microservices.loanms.Mapper.LoansMapper.mapToLoans;
import static com.microservices.loanms.Mapper.LoansMapper.mapToLoansDto;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService{

    LoansRepository loansRepository;

    @Override
    @Transactional
    public void createLoan(String mobileNumber) {

        loansRepository.findByMobileNumber(mobileNumber).ifPresent(
            loansEntity -> {
                throw new LoanAlreadyExistException("Loan with this mobile number Already exist; " + mobileNumber);
            }
        );
        loansRepository.save(createLoanMN(mobileNumber));
    }

    private LoansEntity createLoanMN(String mobileNumber) {
        LoansEntity loansEntity = new LoansEntity();

        Long randNumber = 100000000000L +new Random().nextInt(900000000);

        loansEntity.setLoanNumber(Long.toString(randNumber));
        loansEntity.setLoanType(LoansConstants.HOME_LOAN);
        loansEntity.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loansEntity.setAmountPaid(0);
        loansEntity.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        loansEntity.setMobileNumber(mobileNumber);

        return loansEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public LoansDto fetchLoan(String mobileNumber) {

        LoansEntity resultLoan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException(
                            "loans", "mobile number", mobileNumber);
                });

        return mapToLoansDto(resultLoan, new LoansDto());
    }

    @Override
    @Transactional
    public boolean updateLoan(LoansDto loansDto) {

        LoansEntity resultLoan = loansRepository.findByMobileNumber(loansDto.getMobileNumber())
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException(
                            "loans", "mobile number", loansDto.getMobileNumber());
                });

        // don't know if things are not supposed to be updated so all things are ...
        loansRepository.save(mapToLoans(loansDto, resultLoan));

        return true;
    }

    @Override
    @Transactional
    public boolean deleteLoan(String mobileNumber) {
        LoansEntity resultLoan = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException(
                            "loans", "mobile number", mobileNumber);
                });

        loansRepository.delete(resultLoan);

        return true;
    }
}
