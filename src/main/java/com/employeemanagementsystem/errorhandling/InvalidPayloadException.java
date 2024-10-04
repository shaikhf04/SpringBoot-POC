package com.employeemanagementsystem.errorHandling;

public class InvalidPayloadException extends RuntimeException {
    public InvalidPayloadException(String payloadCannotBeNull) {
        super(payloadCannotBeNull);
    }
}
