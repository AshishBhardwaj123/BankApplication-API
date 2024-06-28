package com.banking.bankapplicationapi.model;

import com.banking.bankapplicationapi.entity.Bank;
import lombok.Data;

@Data
public class BranchRequest {

    private int branchId;
    private int  codeBank;
    private String branchName;
    private String branchAddress;

}
