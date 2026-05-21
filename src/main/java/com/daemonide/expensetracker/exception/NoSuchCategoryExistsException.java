package com.daemonide.expensetracker.exception;

public class NoSuchCategoryExistsException extends RuntimeException {
    public NoSuchCategoryExistsException(String message) {
        super(message);
    }
}

