package com.banking.bankapplicationapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerTO {

    private int accountNo;
    private int loanId;
    private String custName;
    private String custAdress;
    private int custMobileNo;
}
