package com.employeemanagementsystem.errorhandling;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String tokenExpired){
        super(tokenExpired);
    }
}
