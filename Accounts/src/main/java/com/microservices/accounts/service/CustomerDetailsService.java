package com.microservices.accounts.service;

import com.microservices.accounts.Exceptions.ResourceNotFoundException;
import com.microservices.accounts.dto.*;
import com.microservices.accounts.entity.Accounts;
import com.microservices.accounts.entity.Customer;
import com.microservices.accounts.repository.AccountsRepository;
import com.microservices.accounts.repository.CustomerRepository;
import com.microservices.accounts.service.clientFeign.CardsFeignClient;
import com.microservices.accounts.service.clientFeign.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.microservices.accounts.mapper.AccountsMapper.mapToAccountsDto;
import static com.microservices.accounts.mapper.CustomerMapper.mapToCustomerDetailsDto;
import static com.microservices.accounts.mapper.CustomerMapper.mapToCustomerDto;

@Service
@AllArgsConstructor
public class CustomerDetailsService {

    private CustomerRepository customerRepository;

    private AccountsRepository accountsRepository;

    private LoansFeignClient loansFeignClient;

    private CardsFeignClient cardsFeignClient;

    public CustomerDetailsConfig fetchCustomerDetails(String mobileNumber, String correlationId){

        Customer resultCustomer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("customer", "mobile number", mobileNumber));

        Accounts account = accountsRepository.findByCustomerId(resultCustomer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("account", "customer ID", resultCustomer.getCustomerId().toString()));

        CustomerDetailsConfig fetchedCustomerAccountData = mapToCustomerDetailsDto(resultCustomer, new CustomerDetailsConfig());

        ResponseEntity<LoansConfigDto> loansRespEntity = loansFeignClient.fetchLoan(correlationId, mobileNumber);
        ResponseEntity<CardsConfigDto> cardsRespEntity = cardsFeignClient.fetchCard(correlationId, mobileNumber);

        fetchedCustomerAccountData.setAccountsDto(mapToAccountsDto(account, new AccountsDto()));

        if(cardsRespEntity != null){
            fetchedCustomerAccountData.setCardsConfigDto(cardsRespEntity.getBody());
        }

        if (loansRespEntity != null){
            fetchedCustomerAccountData.setLoansConfigDto(loansRespEntity.getBody());
        }

        return fetchedCustomerAccountData;

    }
}
