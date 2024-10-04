package com.employeemanagementsystem.errorhandling;

public class InvalidPayloadException extends RuntimeException {
    public InvalidPayloadException(String payloadCannotBeNull) {
        super(payloadCannotBeNull);
    }
}
