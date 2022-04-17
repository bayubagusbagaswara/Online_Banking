package com.bayu.onlinebanking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "savings_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingsTransaction {

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
            name = "savings_account_id",
            foreignKey = @ForeignKey(name = "fk_savings_transaction_savings_account"),
            referencedColumnName = "id"
    )
    private SavingsAccount savingsAccount;

}
