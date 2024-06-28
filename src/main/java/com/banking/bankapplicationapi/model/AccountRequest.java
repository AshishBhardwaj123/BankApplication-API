package com.banking.bankapplicationapi.model;

import lombok.Data;

@Data
public class AccountRequest {

    private int accountNo;
    private int IdBranch;
    private String accountType;
    private int balance;
}
