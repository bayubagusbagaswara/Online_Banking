package com.bayu.onlinebanking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * PrimaryTransaction adalah transaksi utama
 */
@Entity
@Table(name = "primary_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private double amount;

    @Column(name = "available_balance")
    private BigDecimal availableBalance;

    @ManyToOne
    @JoinColumn(
            name = "id_primary_account",
            foreignKey = @ForeignKey(name = "fk_primary_transaction_primary_account")
    )
    private PrimaryAccount primaryAccount;

}
