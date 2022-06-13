package com.softeam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceNotFoundCode implements ErrorCode{
    ACCOUNT_NOT_FOUND("Ressource ACCOUNT introuvable");

    private final String reasonPhrase;
}
