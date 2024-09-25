package com.employeemanagementsystem.errorHandling;

public class RecordDoesNotExistException extends RuntimeException {
    public RecordDoesNotExistException(String notExists) {
        super(notExists);
    }
}
