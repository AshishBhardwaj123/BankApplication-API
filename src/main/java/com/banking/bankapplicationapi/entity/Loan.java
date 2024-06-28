package com.banking.bankapplicationapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "loan")
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int loanId;
    @Column(name = "loan_type")
    private String loanType;
    @Column(name = "amount")
    private String amount;

    @ManyToOne
    @JoinColumn(name="idbranch")
    private Branch ban;
}
