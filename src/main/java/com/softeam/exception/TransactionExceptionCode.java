package com.softeam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public  enum TransactionExceptionCode implements ErrorCode{
    INVALID_TRANSACTION("Invalid transaction"),
    INSUFFICIENT_BALANCE("Insufficient balance"),
    UNSUPPORTED_TRANSACTION_TYPE("Unsupported transaction type");

    private final String reasonPhrase;
}
