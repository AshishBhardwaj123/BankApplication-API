package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.exception.BranchDetailNotFound;
import com.banking.bankapplicationapi.model.BankTO;
import com.banking.bankapplicationapi.model.BranchRequest;
import com.banking.bankapplicationapi.model.BranchTO;

import java.util.List;

public interface BranchService {
    public List<BranchTO> getAllBranch() throws BranchDetailNotFound;
    public BranchTO getBranchById(int branchId) throws BranchDetailNotFound;
    public BranchTO getBranchByName(String branchName) throws BranchDetailNotFound;
    public BranchTO saveBranch(BranchRequest branchRequest) throws BranchDetailNotFound;
    public BranchTO saveAllBranch(BranchRequest branchRequest) throws BranchDetailNotFound;
    public String DeleteBranch(int branchId) throws BranchDetailNotFound;

}
