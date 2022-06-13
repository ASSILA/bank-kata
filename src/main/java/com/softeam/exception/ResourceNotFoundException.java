package com.softeam.exception;


import lombok.Getter;

/**
 * ResourceNotFoundException .
 */
@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final ResourceNotFoundCode errorCode;

    private final String[] args;

    public ResourceNotFoundException(ResourceNotFoundCode errorCode, String... args) {
        this.errorCode = errorCode;
        this.args = args;
    }
}
