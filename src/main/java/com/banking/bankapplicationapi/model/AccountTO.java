package com.banking.bankapplicationapi.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTO {
    private int branchId;
    private String accountType;
    private int balance;
}
