package com.microservices.loanms.Mapper;

import com.microservices.loanms.Dto.LoansDto;
import com.microservices.loanms.Entity.LoansEntity;

public class LoansMapper {

    public static LoansDto mapToLoansDto(LoansEntity loansEntity, LoansDto loansDto) {
        loansDto.setLoanNumber(loansEntity.getLoanNumber());
        loansDto.setLoanType(loansEntity.getLoanType());
        loansDto.setMobileNumber(loansEntity.getMobileNumber());
        loansDto.setTotalLoan(loansEntity.getTotalLoan());
        loansDto.setAmountPaid(loansEntity.getAmountPaid());
        loansDto.setOutstandingAmount(loansEntity.getOutstandingAmount());
        return loansDto;
    }

    public static LoansEntity mapToLoans(LoansDto loansDto, LoansEntity loansEntity) {
        loansEntity.setLoanNumber(loansDto.getLoanNumber());
        loansEntity.setLoanType(loansDto.getLoanType());
        loansEntity.setMobileNumber(loansDto.getMobileNumber());
        loansEntity.setTotalLoan(loansDto.getTotalLoan());
        loansEntity.setAmountPaid(loansDto.getAmountPaid());
        loansEntity.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loansEntity;
    }
}
