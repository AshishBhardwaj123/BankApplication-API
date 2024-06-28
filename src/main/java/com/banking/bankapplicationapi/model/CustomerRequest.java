package com.banking.bankapplicationapi.model;


import lombok.Data;

@Data
public class CustomerRequest {

    private int custId;
    private int loanId;
    private int accountNo;
    private String custName;
    private String custAddress;
    private int mobileNO;
}
