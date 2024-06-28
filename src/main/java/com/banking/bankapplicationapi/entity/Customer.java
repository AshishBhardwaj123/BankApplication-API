package com.banking.bankapplicationapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "customer")
@Getter
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private int custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_address")
    private String custAddress;
    @Column(name = "mobile_no")
    private int mobileNo;
    @Column(name = "account_no")
    private int accountNo;
    @Column(name = "loan_id")
    private int loanId;

    @OneToMany
    @JoinColumn(name="accountno")
    private Set<Account> accountSet;

    @OneToMany
    @JoinColumn(name="loanid")
    private Set<Loan> loanSet;

}
