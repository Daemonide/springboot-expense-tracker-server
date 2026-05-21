package com.daemonide.expensetracker.controller;

import com.daemonide.expensetracker.dto.LoginRequestDTO;
import com.daemonide.expensetracker.dto.RegisterRequestDTO;
import com.daemonide.expensetracker.exception.ErrorResponse;
import com.daemonide.expensetracker.exception.InvalidLoginException;
import com.daemonide.expensetracker.exception.NoSuchCategoryExistsException;
import com.daemonide.expensetracker.exception.UserAlreadyExistsException;
import com.daemonide.expensetracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request){
        return authService.login(request);
    }

    @ExceptionHandler(value = InvalidLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidLoginException(InvalidLoginException e){
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(),e.getMessage());
    }
}
