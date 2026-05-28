package com.daemonide.expensetracker.controller;

import com.daemonide.expensetracker.dto.ChangePasswordDTO;
import com.daemonide.expensetracker.dto.UpdateAccountDTO;
import com.daemonide.expensetracker.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PutMapping
    public String updateAccount(
            @RequestBody UpdateAccountDTO request,
            Authentication authentication
    ) {
        return accountService.updateAccount(
                authentication.getName(),
                request
        );
    }

    @PutMapping("/password")
    public String changePassword(
            @RequestBody ChangePasswordDTO request,
            Authentication authentication
    ) {
        return accountService.changePassword(
                authentication.getName(),
                request
        );
    }
}