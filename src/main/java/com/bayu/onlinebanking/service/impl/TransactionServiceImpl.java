package com.bayu.onlinebanking.service.impl;

import com.bayu.onlinebanking.entity.*;
import com.bayu.onlinebanking.repository.*;
import com.bayu.onlinebanking.service.TransactionService;
import com.bayu.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
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
        primaryTransactionRepository.save(primaryTransaction);
    }

    @Override
    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionRepository.save(savingsTransaction);
    }

    @Override
    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
        // cek jenis akun asal yakni Primary dan akun tujuannya bertipe Savings
        if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
            // kurangi saldo di primary
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            // tambahkan saldo di savings
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            // simpan perubahan saldo kedua akun
            primaryAccountRepository.save(primaryAccount);
            savingsAccountRepository.save(savingsAccount);

            // catat transfer di transaction
            PrimaryTransaction primaryTransaction = new PrimaryTransaction();
            primaryTransaction.setDate(new Date());
            primaryTransaction.setDescription("Between account transfer from " + transferFrom + " to " + transferTo);
            primaryTransaction.setType("Account");
            primaryTransaction.setStatus("Finished");
            primaryTransaction.setAmount(Double.parseDouble(amount));
            primaryTransaction.setAvailableBalance(primaryAccount.getAccountBalance());
            primaryTransaction.setPrimaryAccount(primaryAccount);
            primaryTransactionRepository.save(primaryTransaction);

        } else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountRepository.save(primaryAccount);
            savingsAccountRepository.save(savingsAccount);

            SavingsTransaction savingsTransaction = new SavingsTransaction();
            savingsTransaction.setDate(new Date());
            savingsTransaction.setDescription("Between account transfer from " + transferFrom + " to " + transferTo);
            savingsTransaction.setType("Transfer");
            savingsTransaction.setStatus("Finished");
            savingsTransaction.setAmount(Double.parseDouble(amount));
            savingsTransaction.setAvailableBalance(savingsAccount.getAccountBalance());
            savingsTransaction.setSavingsAccount(savingsAccount);

            savingsTransactionRepository.save(savingsTransaction);
        } else {
            throw new Exception("Invalid Transfer");
        }
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
