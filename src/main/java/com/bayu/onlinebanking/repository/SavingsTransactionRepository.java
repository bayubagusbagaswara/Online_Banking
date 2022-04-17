package com.bayu.onlinebanking.repository;

import com.bayu.onlinebanking.entity.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingsTransactionRepository extends CrudRepository<SavingsTransaction, Long> {

    List<SavingsTransaction> findAll();
}
