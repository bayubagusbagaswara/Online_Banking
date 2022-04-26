package com.bayu.onlinebanking.controller;

import com.bayu.onlinebanking.service.AccountService;
import com.bayu.onlinebanking.service.TransactionService;
import com.bayu.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

}
