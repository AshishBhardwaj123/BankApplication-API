package com.banking.bankapplicationapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanTO {

    private int idBranch;
    private int loanId;
    private String amount;
    private String loanType;
}
