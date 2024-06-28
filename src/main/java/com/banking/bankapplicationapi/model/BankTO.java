package com.banking.bankapplicationapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BankTO {
    private int bankCode;
    private String bankAddress;
    private String bankName;

    private List<BranchTO> branches;
}
