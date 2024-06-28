package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.entity.Account;
import com.banking.bankapplicationapi.entity.Branch;
import com.banking.bankapplicationapi.exception.AccountDetailNotFound;
import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.exception.BranchDetailNotFound;
import com.banking.bankapplicationapi.model.AccountRequest;
import com.banking.bankapplicationapi.model.AccountTO;
import com.banking.bankapplicationapi.model.BranchTO;
import com.banking.bankapplicationapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    public AccountRepository accountRepository;
    
    @Override
    public List<AccountTO> getAllAccounts() throws AccountDetailNotFound {
    log.info("Inside the AccountServiceImpl");

    List<Account> account = accountRepository.findAll();
        log.info("list of Account,Accounts:{}", account);

        if (CollectionUtils.isEmpty(account)) {
        log.info("Account Details not found");
        throw new AccountDetailNotFound("Account Details not found");
    }
    List<AccountTO> accountTo = account.stream().map(ac -> {
        AccountTO count = new AccountTO();
        count.setAccountType(ac.getAccountType());
        count.setBalance(ac.getBalance());
        return count;
    }).collect(Collectors.toList());
        return accountTo;
}

    @Override
    public AccountTO getAccountByNo(int accountNo) throws AccountDetailNotFound {

        log.info("Inside the BranchServiceImplV1.getBranchById, branchId:{}", accountNo);

        Optional<Account> account2 = accountRepository.findById(accountNo);
        log.info("Account details for the Account No accountNo:{}",accountNo, account2.get());

        if (! account2.isPresent()) {
            log.info("Account details not found for the account No:{}", accountNo);
            throw new  AccountDetailNotFound("Account details not found");
        }

        AccountTO accountTO = new  AccountTO();
        Account acc1 = account2.get();
        accountTO.setAccountType(acc1.getAccountType());
        accountTO.setBalance(acc1.getBalance());

        log.info("End of AccountServiceImplV1.getAccountByNo");
        return  accountTO;
    }


    @Override
    public AccountTO getAccountByType(String accountType) throws AccountDetailNotFound {
        log.info("Inside the BranchServiceImplV1.getBranchById, branchId:{}", accountType);

        Optional<Account> account2 = accountRepository.findByAccountType(accountType);
        log.info("Account details for the Account type accountTypr:{}", account2.get());

        if (! account2.isPresent()) {
            log.info("Account details not found for the account Type:{}", account2);
            throw new  AccountDetailNotFound("Account details not found");
        }
        Account acc1 = account2.get();

        AccountTO accountTO = new  AccountTO();
        accountTO.setAccountType(acc1.getAccountType());
        accountTO.setBalance(acc1.getBalance());

        log.info("End of AccountServiceImplV1.getAccountByNo");
        return  accountTO;
    }

    @Override
    public AccountTO saveAccount(AccountRequest requestAccount) throws AccountDetailNotFound {

        log.info("Inside the Account Request,accountRequeat:{} ", requestAccount);

        Account account3 = new Account();

        account3.setAccountType(requestAccount.getAccountType());
        account3.setBalance(requestAccount.getBalance());
        //account3.

        Branch branch2 = new Branch();
        branch2.setBranchId(requestAccount.getIdBranch());
        account3.setBatch(branch2);

        Account accountResponse = accountRepository.save(account3);

        if (accountResponse == null)
        {
           log.info("Account Details Not Found");
           throw new AccountDetailNotFound();
        }

        AccountTO accountTO3 = new AccountTO();
        accountTO3.setAccountType(account3.getAccountType());
        accountTO3.setBalance(account3.getBalance());

        log.info("End of AccountService");
        return accountTO3;
    }

    @Override
    public AccountTO saveAllAccount(AccountRequest requestAccount) throws AccountDetailNotFound {
        log.info("Inside the Account Request,accountRequeat:{} ", requestAccount);

        Account account3 = new Account();
        account3.setAccountNo(requestAccount.getAccountNo());
        account3.setAccountType(requestAccount.getAccountType());
        account3.setBalance(requestAccount.getBalance());
        //account3.

        Branch branch2 = new Branch();
        branch2.setBranchId(requestAccount.getIdBranch());
        account3.setBatch(branch2);

        Account accountResponse = accountRepository.save(account3);

        if (accountResponse == null)
        {
            log.info("Account Details Not Found");
            throw new AccountDetailNotFound();
        }

        AccountTO accountTO3 = new AccountTO();
        accountTO3.setAccountType(account3.getAccountType());
        accountTO3.setBalance(account3.getBalance());

        log.info("End of AccountService");
        return accountTO3;
    }

    @Override
    public String DeleteAccount(int accountNo) throws AccountDetailNotFound {
        log.info("Inside the bank request , bankRequest:{}", accountNo);

        if (accountNo <= 0) {
            log.info("Invalid Bank Code", accountNo);
            throw new AccountDetailNotFound("Invalid Bank Code");
        }

        accountRepository.deleteById(accountNo);
        return "Bank Details has been Deleted Successfully";
    }

}
