package com.daemonide.expensetracker.util;

public class PasswordValidator {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";

    public static boolean isValid(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }
}