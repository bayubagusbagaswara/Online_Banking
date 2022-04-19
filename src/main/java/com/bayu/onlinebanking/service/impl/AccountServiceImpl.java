package com.bayu.onlinebanking.service.impl;

import com.bayu.onlinebanking.entity.*;
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
import java.util.Date;
import java.util.Locale;

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
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(BigInteger.ZERO));
        savingsAccount.setAccountNumber(accountGen());
        savingsAccountRepository.save(savingsAccount);
        return savingsAccountRepository.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    @Override
    public void deposit(String accountType, double amount, Principal principal) {
        // ambil data user berdasarkan username
        // name diambil dari principal
        User user = userService.findByUsername(principal.getName());

        // cek tipe account, apakah account primary atau account savings
        if (accountType.equalsIgnoreCase("Primary")) {
            // ambil akun utama melalui user
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            // set saldo primary account
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            // simpan perubahan primary account
            primaryAccountRepository.save(primaryAccount);

            // buat transaksi, dan masukan deposit kedalam transaksi
            PrimaryTransaction primaryTransaction = new PrimaryTransaction();
            primaryTransaction.setDate(new Date());
            primaryTransaction.setDescription("Deposit to Primary Account");
            primaryTransaction.setType("Account");
            primaryTransaction.setStatus("Finished");
            primaryTransaction.setAmount(amount);
            primaryTransaction.setAvailableBalance(primaryAccount.getAccountBalance());
            primaryTransaction.setPrimaryAccount(primaryAccount);
            transactionService.savePrimaryDepositTransaction(primaryTransaction);

        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            SavingsTransaction savingsTransaction = new SavingsTransaction();
            savingsTransaction.setDate(new Date());
            savingsTransaction.setDescription("Deposit to savings Account");
            savingsTransaction.setType("Account");
            savingsTransaction.setStatus("Finished");
            savingsTransaction.setAmount(amount);
            savingsTransaction.setAvailableBalance(savingsAccount.getAccountBalance());
            savingsTransaction.setSavingsAccount(savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }

    @Override
    public void withdraw(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);

            PrimaryTransaction primaryTransaction = new PrimaryTransaction();
            primaryTransaction.setDate(new Date());
            primaryTransaction.setDescription("Withdraw from Primary Account");
            primaryTransaction.setType("Account");
            primaryTransaction.setStatus("Finished");
            primaryTransaction.setAmount(amount);
            primaryTransaction.setAvailableBalance(primaryAccount.getAccountBalance());
            primaryTransaction.setPrimaryAccount(primaryAccount);

            transactionService.savePrimaryWithdrawTransaction(primaryTransaction);

        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountRepository.save(savingsAccount);

            SavingsTransaction savingsTransaction = new SavingsTransaction();
            savingsTransaction.setDate(new Date());
            savingsTransaction.setDescription("Withdraw from Savings Account");
            savingsTransaction.setType("Account");
            savingsTransaction.setStatus("Finished");
            savingsTransaction.setAmount(amount);
            savingsTransaction.setAvailableBalance(savingsAccount.getAccountBalance());
            savingsTransaction.setSavingsAccount(savingsAccount);
            transactionService.saveSavingsWithdrawTransaction(savingsTransaction);
        }
    }

    private int accountGen() {
        return ++nextAccountNumber;
    }
}
