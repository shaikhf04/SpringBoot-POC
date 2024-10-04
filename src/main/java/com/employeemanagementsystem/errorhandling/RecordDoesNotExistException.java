package com.employeemanagementsystem.errorhandling;

public class RecordDoesNotExistException extends RuntimeException {
    public RecordDoesNotExistException(String notExists) {
        super(notExists);
    }
}
