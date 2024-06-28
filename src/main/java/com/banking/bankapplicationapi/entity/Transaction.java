package com.banking.bankapplicationapi.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@Table(name = "transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "trans_type")
    private String transType;
    @Column(name = "amount")
    private int amount;
    @Column(name = "date")
    private Date date;
    @Column(name = "custid")
    private int custId;
}
