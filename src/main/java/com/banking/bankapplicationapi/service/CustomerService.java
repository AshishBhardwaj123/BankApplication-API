package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.exception.CustomerDetailNotFound;
import com.banking.bankapplicationapi.exception.LoanDetailNotFound;
import com.banking.bankapplicationapi.model.CustomerRequest;
import com.banking.bankapplicationapi.model.CustomerTO;
import com.banking.bankapplicationapi.model.LoanTO;

import java.util.List;

public interface CustomerService {

    public List<CustomerTO> getAllCustomers() throws CustomerDetailNotFound;
    public CustomerTO getCustomersById(int custId) throws CustomerDetailNotFound;
    public CustomerTO getCustomersByName(String string) throws CustomerDetailNotFound;
    public CustomerTO saveCustomer(CustomerRequest customerRequest) throws CustomerDetailNotFound;
    public CustomerTO saveAllCustomer(CustomerRequest customerRequest) throws CustomerDetailNotFound;
    public String DeleteCustomer(int customerId) throws CustomerDetailNotFound;
}


