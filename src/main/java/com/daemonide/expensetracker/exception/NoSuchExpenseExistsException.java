package com.daemonide.expensetracker.exception;

public class NoSuchExpenseExistsException extends RuntimeException {
    private String message;
    public NoSuchExpenseExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
