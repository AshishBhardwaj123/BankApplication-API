package com.banking.bankapplicationapi.controller;

import com.banking.bankapplicationapi.entity.Customer;
import com.banking.bankapplicationapi.entity.Loan;
import com.banking.bankapplicationapi.exception.AccountDetailNotFound;
import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.exception.CustomerDetailNotFound;
import com.banking.bankapplicationapi.exception.LoanDetailNotFound;
import com.banking.bankapplicationapi.model.AccountTO;
import com.banking.bankapplicationapi.model.CustomerRequest;
import com.banking.bankapplicationapi.model.CustomerTO;
import com.banking.bankapplicationapi.model.LoanTO;
import com.banking.bankapplicationapi.repository.CustomerRepository;
import com.banking.bankapplicationapi.repository.LoanRepository;
import com.banking.bankapplicationapi.service.CustomerService;
import com.banking.bankapplicationapi.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerTO>> getCustomers() {
        log.info("Inside the BankControllerV1.getBanks");
        List<CustomerTO> customerTos = null;

        try {
            customerTos = customerService.getAllCustomers();
            log.info("List of branch, branches:{}", customerTos);
        } catch (CustomerDetailNotFound cs) {
            log.error("Branch details not found", cs);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception cs1) {
            log.error("Exception while getting the data for get all branch");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankControllerV1.getBanks");
        return new ResponseEntity<>(customerTos, HttpStatus.OK);
    }

    @GetMapping("/{CustomerById}")
    public ResponseEntity<CustomerTO> getCustomerById(@PathVariable("Customer By Id") int custId) {
        log.info("Inside the AccountControllerV1.getAccountByNo");
        CustomerTO customerTO = null;

        try {
            customerTO = customerService.getCustomersById(custId);
            log.info("Bank details for the bank code, bank code:{}", custId);
        } catch (CustomerDetailNotFound ac1) {
            log.error("Bank details not found", ac1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the Account details by Account Number", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(customerTO, HttpStatus.OK);
    }

    @GetMapping("/{CustomerByName}")
    public ResponseEntity<CustomerTO> getCustomerByName(@PathVariable("CustomerByName") String custName) {
        log.info("Inside the AccountControllerV1.getAccountByName");
        CustomerTO customerTO2 = null;

        try {
            customerTO2 = customerService.getCustomersByName(custName);
            log.info("Account details for the Account Name, account Name:{}", custName);
        } catch (CustomerDetailNotFound ac1) {
            log.error("Account details not found", ac1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the Account details by Account Name", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankByName");
        return new ResponseEntity<>(customerTO2, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerTO> saveCustomer(@RequestBody CustomerRequest responseCustomer) {
        log.info("Inside the AccountControllerV1.getAccountByName");
        CustomerTO customerTO3 = null;

        if(responseCustomer == null)
        {
            log.info("Invalid REQUEST bankRequest:{}",responseCustomer);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        try {
            customerTO3 = customerService.saveCustomer(responseCustomer);
            log.info("Account details for the Account Name, account Name:{}", responseCustomer);
        } catch (CustomerDetailNotFound ac1) {
            log.error("Account details not found", ac1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the Account details by Account Name", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankByName");
        return new ResponseEntity<>(customerTO3, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomerTO> saveAllCustomer(@RequestBody CustomerRequest responseCustomer) {
        log.info("Inside the AccountControllerV1.getAccountByName");
        CustomerTO customerTO3 = null;

        if(responseCustomer == null)
        {
            log.info("Invalid REQUEST bankRequest:{}",responseCustomer);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        try {
            customerTO3 = customerService.saveAllCustomer(responseCustomer);
            log.info("Account details for the Account Name, account Name:{}", responseCustomer);
        } catch (CustomerDetailNotFound ac1) {
            log.error("Account details not found", ac1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the Account details by Account Name", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankByName");
        return new ResponseEntity<>(customerTO3, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> DeleteCustomer(@RequestParam("Delete") int custId) {
        log.info("Inside the BankControllerV1.getBankByName");
        String res = null;

        if(custId <= 0)
        {
            log.info("Invalid REQUEST delteBankRequest:{}",custId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
              res = customerService.DeleteCustomer(custId);
            log.info("Bank details for the bank code, bank code:{}", custId);
        } catch (CustomerDetailNotFound ex1) {
            log.error("Bank details not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the bank details by bank code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }
}

