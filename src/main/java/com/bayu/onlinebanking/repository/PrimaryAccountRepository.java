package com.bayu.onlinebanking.repository;

import com.bayu.onlinebanking.entity.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountRepository extends CrudRepository<PrimaryAccount, Long> {

    PrimaryAccount findByAccountNumber(int accountNumber);
}
