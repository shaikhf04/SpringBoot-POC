package com.employeemanagementsystem.errorhandling;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String alreadyExists) {
        super(alreadyExists);
    }
}
