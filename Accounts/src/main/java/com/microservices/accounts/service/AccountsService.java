package com.microservices.accounts.service;

import com.microservices.accounts.Constants.Constants;
import com.microservices.accounts.Exceptions.CustomerAlreadyExistException;
import com.microservices.accounts.Exceptions.ResourceNotFoundException;
import com.microservices.accounts.dto.AccountsDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Accounts;
import com.microservices.accounts.entity.Customer;
import com.microservices.accounts.mapper.AccountsMapper;
import com.microservices.accounts.mapper.CustomerMapper;
import com.microservices.accounts.repository.AccountsRepository;
import com.microservices.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static com.microservices.accounts.mapper.AccountsMapper.mapToAccountsDto;
import static com.microservices.accounts.mapper.CustomerMapper.mapToCustomer;
import static com.microservices.accounts.mapper.CustomerMapper.mapToCustomerDto;

@Service
@AllArgsConstructor
public class AccountsService implements IAccountsService{

    AccountsRepository accountsRepository;
    CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto){

        Customer customer = mapToCustomer(customerDto, new Customer());
        Optional<Customer> checkIfCustomerExist = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if(checkIfCustomerExist.isPresent()){
            throw new CustomerAlreadyExistException("This phone number already exists " + checkIfCustomerExist);
        }

        Customer savedCustomer = customerRepository.save(customer);

        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer savedCustomer){

        Accounts account = new Accounts();
        account.setAccountType(Constants.SAVINGS);
        long randAccountNumb = 1000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(randAccountNumb);
        account.setCustomerId(savedCustomer.getCustomerId());
        account.setBranchAddress(Constants.ADDRESS);

        return account;
    }

    @Override
    public CustomerDto fetchCustomerAccountData(String mobileNumber){

        Customer customer = (customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("customer", "mobile number", mobileNumber)));

        Accounts account = (accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("accounts", "customer Id", customer.getCustomerId().toString())));

        CustomerDto fetchedCustomerAccountData = mapToCustomerDto(customer, new CustomerDto());
        fetchedCustomerAccountData.setAccountsDto(mapToAccountsDto(account, new AccountsDto()));

        return fetchedCustomerAccountData;
    }

    @Override
    @Transactional
    public CustomerDto updateCustomerData(CustomerDto customerDto){

        if (customerDto == null) {
            throw new IllegalArgumentException("CustomerDto cannot be null.");
        }

        AccountsDto accountDto = customerDto.getAccountsDto();
        if (accountDto == null) {
            throw new IllegalArgumentException("AccountDto cannot be null.");
        }

        Accounts account = accountsRepository.findById(accountDto.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Account", "AccountNumber", accountDto.getAccountNumber().toString()));

        AccountsMapper.mapToAccounts(accountDto, account);
        accountsRepository.save(account);

        Customer customer = customerRepository.findById(account.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Customer", "Customer Id", account.getCustomerId().toString()));

        CustomerMapper.mapToCustomer(customerDto, customer);
        Customer updatedCustomer = customerRepository.save(customer);

        return CustomerMapper.mapToCustomerDto(updatedCustomer, customerDto);
    }

    @Override
    public boolean deleteCustomerData(String mobileNumber) {

            Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                    .orElseThrow(() -> new ResourceNotFoundException
                            ("Customer", "mobileNumber", mobileNumber));
            customerRepository.deleteById(customer.getCustomerId());
            accountsRepository.deleteByCustomerId(customer.getCustomerId());


        return true;
    }

}
