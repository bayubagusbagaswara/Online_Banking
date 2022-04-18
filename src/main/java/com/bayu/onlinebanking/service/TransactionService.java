package com.bayu.onlinebanking.service;

import com.bayu.onlinebanking.entity.*;

import java.security.Principal;
import java.util.List;

public interface TransactionService {

    // cari semua transaksi dari akun utama
    List<PrimaryTransaction> findPrimaryTransactionList(String username);

    // cari semua transaksi dari akun tabungan
    List<SavingsTransaction> findSavingsTransactionList(String username);

    // simpan transaksi deposit ke akun utama
    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    // simpan transaksi deposit ke akun tabungan atau savings
    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    // simpan transaksi withdraw dari akun utama
    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);

    // simpan transaksi withdraw dari akun tabungan
    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);

    // transfer antar akun, tapi satu user
    void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception;

    // penerima adalah orang/akun yang menerima transfer dari kita
    // dan penerima ini adalah orang yang sering menerima transfer kita, atau bisa dibilang favorit
    // cari semua daftar penerima
    List<Recipient> findRecipientList(Principal principal);

    // simpan data penerima
    Recipient saveRecipient(Recipient recipient);

    // cari penerima berdasarkan name
    Recipient findRecipientByName(String recipientName);

    // hapus penerima berdasarkan name
    void deleteRecipientByName(String recipientName);

    // transfer ke orang lain
    void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount);
}
