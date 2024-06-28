package com.banking.bankapplicationapi.controller;

import com.banking.bankapplicationapi.exception.AccountDetailNotFound;
import com.banking.bankapplicationapi.model.AccountRequest;
import com.banking.bankapplicationapi.model.AccountTO;
import com.banking.bankapplicationapi.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountTO>> getAccounts() {
        log.info("Inside the AccountControllerV1.getAccounts");
        List<AccountTO> accountTos = null;

        try
        {
            accountTos = accountService.getAllAccounts();
            log.info("List of branch, branches:{}", accountTos);
        }
        catch (AccountDetailNotFound ac)
        {
            log.error("Account details not found", ac);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception ac2)
        {
            log.error("Exception while getting the data for get all Accounts");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of AccountController.getAccounts");
        return new ResponseEntity<>(accountTos, HttpStatus.OK);
    }

    @GetMapping("/{ByAccountNo}")
    public ResponseEntity<AccountTO> getAccountByNo(@PathVariable("ByAccountNo") int accNo)
    {
        log.info("Inside the AccountController.getAccountByNo");

        if(accNo <=0)
        {
            log.info("Invalid Account Number, accountNumber:{}", accNo);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AccountTO accountTO= null;

        try {
            accountTO = accountService.getAccountByNo(accNo);
            log.info("Account details for the Account Number , Account Number:{}", accNo);

            if(accountTO == null)
            {
                log.info("No data found for the account number, accountNo:{}", accNo);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex)
        {
            log.error("Exception while getting the Account details by Account Number", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(accountTO, HttpStatus.OK);
    }

    @GetMapping("/AccountByType")
    public ResponseEntity<AccountTO> getAccountByType(@RequestParam("AccountByType") String accountName){
        log.info("Inside the AccountControllerV1.getAccountByType");
        AccountTO accountTO2 = null;

        try {
            accountTO2 = accountService.getAccountByType(accountName);
            log.info("Account details for the Account Type, account Type:{}", accountName);
        } catch (AccountDetailNotFound ac1){
            log.error("Account details not found", ac1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ac){
            log.error("Exception while getting the Account details by Account Name", ac);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankByName");
        return new ResponseEntity<>(accountTO2, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountTO> saveAccounts(@RequestBody AccountRequest accountRequest){
        log.info("Inside the AccountControllerV1.SaveBanks");
        AccountTO accountTO2 = null;

        try {
            accountTO2 = accountService.saveAccount(accountRequest);
            log.info("Account details for the Account Type, account Type:{}", accountRequest);
        } catch (AccountDetailNotFound ac1){
            log.error("Account details not found", ac1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ac){
            log.error("Exception while getting the Account details by Account Name", ac);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankByName");
        return new ResponseEntity<>(accountTO2, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AccountTO> saveAllAccounts(@RequestBody AccountRequest accountRequest){
        log.info("Inside the AccountControllerV1.SaveBanks");
        AccountTO accountTO2 = null;

        try {
            accountTO2 = accountService.saveAllAccount(accountRequest);
            log.info("Account details for the Account Type, account Type:{}", accountRequest);
        } catch (AccountDetailNotFound ac1){
            log.error("Account details not found", ac1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ac){
            log.error("Exception while getting the Account details by Account Name", ac);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankByName");
        return new ResponseEntity<>(accountTO2, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> DeleteAccount(@RequestParam("Delete") int accountNo) {
        log.info("Inside the BankControllerV1.getBankByName");
        String res = null;

        if(accountNo <= 0)
        {
            log.info("Invalid REQUEST delteBankRequest:{}",accountNo);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
              res = accountService.DeleteAccount(accountNo);
            log.info("Bank details for the bank code, bank code:{}",accountNo);
        } catch (AccountDetailNotFound ex1) {
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

