package com.bayu.onlinebanking.service.impl;

import com.bayu.onlinebanking.entity.PrimaryAccount;
import com.bayu.onlinebanking.entity.SavingsAccount;
import com.bayu.onlinebanking.repository.PrimaryAccountRepository;
import com.bayu.onlinebanking.repository.SavingsAccountRepository;
import com.bayu.onlinebanking.service.AccountService;
import com.bayu.onlinebanking.service.TransactionService;
import com.bayu.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Principal;

@Service
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNumber = 11223101;

    private final PrimaryAccountRepository primaryAccountRepository;
    private final SavingsAccountRepository savingsAccountRepository;
    private final UserService userService;
    private final TransactionService transactionService;

    @Autowired
    public AccountServiceImpl(PrimaryAccountRepository primaryAccountRepository, SavingsAccountRepository savingsAccountRepository, UserService userService, TransactionService transactionService) {
        this.primaryAccountRepository = primaryAccountRepository;
        this.savingsAccountRepository = savingsAccountRepository;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @Override
    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(BigInteger.ZERO));
        primaryAccount.setAccountNumber(accountGen());

        primaryAccountRepository.save(primaryAccount);
        return primaryAccountRepository.findByAccountNumber(primaryAccount.getAccountNumber());
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        return null;
    }

    @Override
    public void deposit(String accountType, double amount, Principal principal) {

    }

    @Override
    public void withdraw(String accountType, double amount, Principal principal) {

    }

    private int accountGen() {
        return ++nextAccountNumber;
    }
}
