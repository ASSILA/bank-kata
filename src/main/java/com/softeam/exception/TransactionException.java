package com.softeam.exception;

import java.io.Serializable;

public class TransactionException extends RuntimeException implements Serializable {
    private final TransactionExceptionCode errorCode;

    private final String[] args;

    public TransactionException(TransactionExceptionCode errorCode, String... args) {
        this.errorCode = errorCode;
        this.args = args;
    }
}
