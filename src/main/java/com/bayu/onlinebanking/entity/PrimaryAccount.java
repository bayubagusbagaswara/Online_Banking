package com.bayu.onlinebanking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "primary_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false)
    private int accountNumber;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

}
