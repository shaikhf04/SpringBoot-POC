package com.employeemanagementsystem.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandling {

    //Custom exception
    @ExceptionHandler(RecordDoesNotExistException.class)
    public ResponseEntity<Error> notExists(RecordDoesNotExistException recordDoesNotExistException){
        Error error = new Error();
        error.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST));
        error.setErrorMessage(recordDoesNotExistException.getMessage());
        return ResponseEntity.ok(error);
    }

    //Custom exception
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Error> employeeNotFound(EmployeeNotFoundException employeeNotFoundException){
        Error error = new Error();
        error.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST));
        error.setErrorMessage(employeeNotFoundException.getMessage());
        return ResponseEntity.ok(error);
    }

    //During validation if it caught any exception MethodArgumentNotValidException Handles it by default
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> invalidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Error error = new Error();
        error.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST));
        error.setErrorMessage(methodArgumentNotValidException.getMessage());
        return ResponseEntity.ok(error);
    }
}
