package com.banking.bankapplicationapi.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "bank")
public class Bank implements Serializable {
    public static final long serialVersionUID = 4543545l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_code")
    private int bankCode;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_address")
    private String bankAddress;

    @OneToMany(mappedBy = "bank",cascade = CascadeType.ALL)
    private Set<Branch> branchSet;
}
