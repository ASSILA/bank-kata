package com.softeam.exception;

public class InsufficientBalanceException extends TransactionException {

    public InsufficientBalanceException(String... args) {

        super(TransactionExceptionCode.INSUFFICIENT_BALANCE, args);
    }
}
