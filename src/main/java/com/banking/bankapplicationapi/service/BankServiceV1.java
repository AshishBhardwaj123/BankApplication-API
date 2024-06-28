package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.model.BankRequest;
import com.banking.bankapplicationapi.model.BankTO;

import java.util.List;

public interface BankServiceV1 {
    public List<BankTO> getAllBanks() throws BankDetailNotFound;
    public BankTO getBankByCode(int bankCode) throws BankDetailNotFound;
    public BankTO getBankByName(String bankName) throws BankDetailNotFound;
    public BankTO saveBank(BankRequest bankRequest) throws BankDetailNotFound;
    public BankTO saveAllBank(BankRequest saveBank) throws BankDetailNotFound;
    public String DeleteBank(int argument) throws BankDetailNotFound;
}
