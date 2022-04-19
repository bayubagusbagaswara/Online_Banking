package com.bayu.onlinebanking.service.impl;

import com.bayu.onlinebanking.entity.*;
import com.bayu.onlinebanking.repository.*;
import com.bayu.onlinebanking.service.TransactionService;
import com.bayu.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final UserService userService;
    private final PrimaryTransactionRepository primaryTransactionRepository;
    private final SavingsTransactionRepository savingsTransactionRepository;
    private final PrimaryAccountRepository primaryAccountRepository;
    private final SavingsAccountRepository savingsAccountRepository;
    private final RecipientRepository recipientRepository;

    @Autowired
    public TransactionServiceImpl(UserService userService, PrimaryTransactionRepository primaryTransactionRepository, SavingsTransactionRepository savingsTransactionRepository, PrimaryAccountRepository primaryAccountRepository, SavingsAccountRepository savingsAccountRepository, RecipientRepository recipientRepository) {
        this.userService = userService;
        this.primaryTransactionRepository = primaryTransactionRepository;
        this.savingsTransactionRepository = savingsTransactionRepository;
        this.primaryAccountRepository = primaryAccountRepository;
        this.savingsAccountRepository = savingsAccountRepository;
        this.recipientRepository = recipientRepository;
    }

    @Override
    public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
        User user = userService.findByUsername(username);
        final List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();
        return primaryTransactionList;
    }

    @Override
    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        final User user = userService.findByUsername(username);
        final List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();
        return savingsTransactionList;
    }

    @Override
    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
        primaryTransactionRepository.save(primaryTransaction);
    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionRepository.save(savingsTransaction);
    }

    @Override
    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {

    }

    @Override
    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {

    }

    @Override
    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {

    }

    @Override
    public List<Recipient> findRecipientList(Principal principal) {
        return null;
    }

    @Override
    public Recipient saveRecipient(Recipient recipient) {
        return null;
    }

    @Override
    public Recipient findRecipientByName(String recipientName) {
        return null;
    }

    @Override
    public void deleteRecipientByName(String recipientName) {

    }

    @Override
    public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {

    }
}
