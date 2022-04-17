package com.bayu.onlinebanking.repository;

import com.bayu.onlinebanking.entity.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrimaryTransactionRepository extends CrudRepository<PrimaryTransaction, Long> {

    List<PrimaryTransaction> findAll();
}
