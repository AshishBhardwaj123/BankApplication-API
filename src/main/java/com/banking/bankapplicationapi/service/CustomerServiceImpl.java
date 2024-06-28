package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.entity.Account;
import com.banking.bankapplicationapi.entity.Customer;
import com.banking.bankapplicationapi.exception.AccountDetailNotFound;
import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.exception.CustomerDetailNotFound;
import com.banking.bankapplicationapi.model.AccountTO;
import com.banking.bankapplicationapi.model.CustomerRequest;
import com.banking.bankapplicationapi.model.CustomerTO;
import com.banking.bankapplicationapi.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    public CustomerRepository customerRepository;
    @Override
    public List<CustomerTO> getAllCustomers() throws CustomerDetailNotFound {

        log.info("Inside the CustomerServiceImpl");

        List<Customer> customer = customerRepository.findAll();
        log.info("list of Customer,Customers:{}", customer);

        if (CollectionUtils.isEmpty(customer)) {
            log.info("Customer Details not found");
            throw new CustomerDetailNotFound("Customer Details not found");
        }
        List<CustomerTO> customerTo = customer.stream().map(cust -> {
            CustomerTO custTO = new CustomerTO();
            custTO.setCustName(cust.getCustName());
            custTO.setCustAdress(cust.getCustAddress());
            custTO.setCustMobileNo(cust.getMobileNo());
            return custTO;
        }).collect(Collectors.toList());
        return customerTo;
    }

    @Override
    public CustomerTO getCustomersById(int custId) throws CustomerDetailNotFound {

            log.info("Inside the BranchServiceImplV1.getBranchById, branchId:{}", custId);

            Optional<Customer> customer2 = customerRepository.findById(custId);
            log.info("Account details for the Account No accountNo:{}", customer2.get());

            if (! customer2.isPresent()) {
                log.info("Account details not found for the account No:{}", custId);
                throw new  CustomerDetailNotFound("Account details not found");
            }
            Customer cust1 = customer2.get();

            CustomerTO customerTO = new  CustomerTO();
            customerTO.setCustName(cust1.getCustName());
            customerTO.setCustAdress(cust1.getCustAddress());
            customerTO.setCustMobileNo(cust1.getMobileNo());

            log.info("End of AccountServiceImplV1.getAccountByNo");
            return  customerTO;
        }

    @Override
    public CustomerTO getCustomersByName(String custName) throws CustomerDetailNotFound {
        log.info("Inside the BranchServiceImplV1.getBranchById, branchId:{}", custName);

        Optional<Customer> customer3 = customerRepository.findByCustName(custName);
        log.info("Account details for the Account No accountNo:{}", customer3.get());

        if (! customer3.isPresent()) {
            log.info("Account details not found for the account No:{}", custName);
            throw new  CustomerDetailNotFound("Account details not found");
        }
        Customer cust1 = customer3.get();

        CustomerTO customerTO = new  CustomerTO();
        customerTO.setCustName(cust1.getCustName());
        customerTO.setCustAdress(cust1.getCustAddress());
        customerTO.setCustMobileNo(cust1.getMobileNo());

        log.info("End of AccountServiceImplV1.getAccountByNo");
        return  customerTO;
    }

    @Override
    public CustomerTO saveCustomer(CustomerRequest customerRequest) throws CustomerDetailNotFound {

        log.info("Inside the CustomerServiceImpl.saveCustomers:{}",customerRequest);

        Customer response = new Customer();
        response.setCustName(customerRequest.getCustName());
        response.setCustAddress(customerRequest.getCustAddress());
        response.setMobileNo(customerRequest.getMobileNO());
        response.setLoanId(customerRequest.getLoanId());
        response.setAccountNo(customerRequest.getAccountNo());

        if (response == null)
        {
            log.info("Customer is empty",response);
            throw new CustomerDetailNotFound();
        }

        CustomerTO customerTO3 = new CustomerTO();
        customerTO3.setCustName(customerRequest.getCustName());
        customerTO3.setCustAdress(customerRequest.getCustAddress());
        customerTO3.setCustMobileNo(customerRequest.getMobileNO());

        log.info("End of CustomerServiceImpl.saveCustomers",customerTO3);
        return customerTO3;
    }

    @Override
    public CustomerTO saveAllCustomer(CustomerRequest customerRequest) throws CustomerDetailNotFound {
        log.info("Inside the CustomerServiceImpl.saveCustomers:{}",customerRequest);

        Customer response = new Customer();
        response.setCustName(customerRequest.getCustName());
        response.setCustAddress(customerRequest.getCustAddress());
        response.setMobileNo(customerRequest.getMobileNO());
        response.setLoanId(customerRequest.getLoanId());
        response.setAccountNo(customerRequest.getAccountNo());

        if (response == null)
        {
            log.info("Customer is empty",response);
            throw new CustomerDetailNotFound();
        }

        CustomerTO customerTO3 = new CustomerTO();
        customerTO3.setCustName(customerRequest.getCustName());
        customerTO3.setCustAdress(customerRequest.getCustAddress());
        customerTO3.setCustMobileNo(customerRequest.getMobileNO());

        log.info("End of CustomerServiceImpl.saveCustomers",customerTO3);
        return customerTO3;
    }

    @Override
    public String DeleteCustomer(int customerId) throws CustomerDetailNotFound {
        log.info("Inside the Delte Customer , bankRequest:{}", customerId);

        String delete = null;
        if (customerId <= 0) {
            log.info("Invalid Bank Code", customerId);
            throw new CustomerDetailNotFound("Invalid Customer Id");
        }

        customerRepository.deleteById(customerId);
        return "Bank Details has been Deleted Successfully";
    }
}


