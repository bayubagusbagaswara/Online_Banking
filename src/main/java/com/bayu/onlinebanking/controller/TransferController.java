package com.bayu.onlinebanking.controller;

import com.bayu.onlinebanking.service.TransactionService;
import com.bayu.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    private final TransactionService transactionService;

    private final UserService userService;

    @Autowired
    public TransferController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }
}
