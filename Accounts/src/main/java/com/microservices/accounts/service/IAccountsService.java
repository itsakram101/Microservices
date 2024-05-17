package com.microservices.accounts.service;


import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Customer;
import jakarta.validation.Valid;

import java.util.Optional;

public interface IAccountsService {

    void createAccount(@Valid CustomerDto customerDto);

    CustomerDto fetchCustomerAccountData(String mobileNumber);

    CustomerDto updateCustomerData(CustomerDto customerDto);

    boolean deleteCustomerData(String mobileNumber);
}
