package com.microservices.accounts.mapper;

import com.microservices.accounts.dto.CustomerDetailsConfig;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Customer;

import java.util.Optional;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerDetailsConfig mapToCustomerDetailsDto(Customer customer, CustomerDetailsConfig customerDetailsConfig){
        customerDetailsConfig.setName(customer.getName());
        customerDetailsConfig.setEmail(customer.getEmail());
        customerDetailsConfig.setMobileNumber(customer.getMobileNumber());
        return customerDetailsConfig;
    }
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
