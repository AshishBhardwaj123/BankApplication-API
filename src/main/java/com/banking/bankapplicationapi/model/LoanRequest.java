package com.banking.bankapplicationapi.model;

import lombok.Data;

@Data
public class LoanRequest {

    private int loanId;
    private int idBranch;
    private String loanType;
    private String amount;


}
