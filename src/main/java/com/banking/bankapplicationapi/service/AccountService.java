package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.exception.AccountDetailNotFound;
import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.exception.BranchDetailNotFound;
import com.banking.bankapplicationapi.model.AccountRequest;
import com.banking.bankapplicationapi.model.AccountTO;
import com.banking.bankapplicationapi.model.BranchTO;

import java.util.List;

public interface AccountService {
    public List<AccountTO> getAllAccounts() throws AccountDetailNotFound;

    AccountTO getAccountByNo(int accountNo) throws AccountDetailNotFound;

    AccountTO getAccountByType(String accountType) throws AccountDetailNotFound;

    AccountTO saveAccount(AccountRequest requestAccount) throws AccountDetailNotFound;

    AccountTO saveAllAccount(AccountRequest requestAccount) throws AccountDetailNotFound;

    public String DeleteAccount(int accountNo) throws AccountDetailNotFound;
}
