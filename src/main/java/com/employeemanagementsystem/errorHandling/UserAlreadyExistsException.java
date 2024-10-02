package com.employeemanagementsystem.errorHandling;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String alreadyExists) {
        super(alreadyExists);
    }
}
