package com.banking.bankapplicationapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BranchTO {
    private int codeBank;
    private String branchName;
    private String branchAddress;

   // private BankTO toBank;
    //private String bankName;
    //private String bankAddress;

   // private Set<BranchTO> branches2;
}
