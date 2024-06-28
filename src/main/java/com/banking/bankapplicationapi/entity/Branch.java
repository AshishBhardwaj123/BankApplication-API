package com.banking.bankapplicationapi.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "branch")
public class Branch implements Serializable {
    public static final long  serialVersionUID = 1878678757;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private int branchId;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "branch_address")
    private String branchAddress;


    @ManyToOne
    @JoinColumn(name="bankcode")
    private Bank bank;

    @OneToMany(mappedBy = "batch",cascade = CascadeType.ALL)
     private Set<Account> accountSet;

}
