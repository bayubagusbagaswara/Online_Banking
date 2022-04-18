package com.bayu.onlinebanking.service;

import com.bayu.onlinebanking.entity.PrimaryAccount;
import com.bayu.onlinebanking.entity.SavingsAccount;

import java.security.Principal;

public interface AccountService {

    // create akun utama
    PrimaryAccount createPrimaryAccount();

    // create akun tabungan/savings
    SavingsAccount createSavingsAccount();

    // deposit atau setoran
    // Principal digunakan untuk mendapatkan data User yang saat ini login
    void deposit(String accountType, double amount, Principal principal);

    // withdraw atau penarikan
    void withdraw(String accountType, double amount, Principal principal);
}
