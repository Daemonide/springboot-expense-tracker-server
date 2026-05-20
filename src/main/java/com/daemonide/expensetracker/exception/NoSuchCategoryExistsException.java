package com.daemonide.expensetracker.exception;

public class NoSuchCategoryExistsException extends RuntimeException {
    private String message;
    public NoSuchCategoryExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}

