package com.banking.bankapplicationapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Entity
@Data
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_no")
    private int accountNo;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "balance")
    private int balance;

    @ManyToOne
    @JoinColumn(name="branchid")
    private Branch batch;

    //private Set<Account> accountSet;
}
