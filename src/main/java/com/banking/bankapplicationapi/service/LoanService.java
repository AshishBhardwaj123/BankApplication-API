package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.exception.BranchDetailNotFound;
import com.banking.bankapplicationapi.exception.LoanDetailNotFound;
import com.banking.bankapplicationapi.model.BranchTO;
import com.banking.bankapplicationapi.model.LoanRequest;
import com.banking.bankapplicationapi.model.LoanTO;

import java.util.List;

public interface LoanService {

    public List<LoanTO> getAllLoans() throws LoanDetailNotFound;
    LoanTO getLoanById(int loanId) throws LoanDetailNotFound;

    LoanTO getLoanByType(String loanType) throws LoanDetailNotFound;

    LoanTO saveLoans(LoanRequest loanRequest) throws LoanDetailNotFound;

    LoanTO saveAllLoans(LoanRequest loanRequest) throws LoanDetailNotFound;

    String DeleteLoan(int loanId) throws LoanDetailNotFound;

    //public String DeleteLoan(int loanId) throws LoanDetailNotFound;
}
