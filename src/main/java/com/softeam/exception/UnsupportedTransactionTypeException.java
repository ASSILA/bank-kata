package com.softeam.exception;

public class UnsupportedTransactionTypeException extends TransactionException{
    public UnsupportedTransactionTypeException(String... args) {
        super(TransactionExceptionCode.UNSUPPORTED_TRANSACTION_TYPE, args);
    }
}
