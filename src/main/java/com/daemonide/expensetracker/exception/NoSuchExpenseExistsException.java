package com.daemonide.expensetracker.exception;

public class NoSuchExpenseExistsException extends RuntimeException {
    public NoSuchExpenseExistsException(String message) {
        super(message);
    }
}
