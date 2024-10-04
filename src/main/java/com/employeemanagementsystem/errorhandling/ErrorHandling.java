package com.employeemanagementsystem.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandling {

    @ExceptionHandler({RecordDoesNotExistException.class,EmployeeNotFoundException.class})
    public ResponseEntity<Error> notExists(RecordDoesNotExistException recordDoesNotExistException) {
        Error error = new Error();
        error.setErrorCode("Record Does Not Exist!");
        error.setErrorMessage(recordDoesNotExistException.getMessage());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Error> invalidException(UserAlreadyExistsException userAlreadyExistsException) {
        Error error = new Error();
        error.setErrorCode("Record Already Exists!");
        error.setErrorMessage(userAlreadyExistsException.getMessage());
        return ResponseEntity.ok(error);
    }

    //During validation if it caught any exception MethodArgumentNotValidException Handles it by default
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> invalidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Error error = new Error();
        error.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST));
        error.setErrorMessage(methodArgumentNotValidException.getMessage());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> generalException(Exception exception) {
        Error error = new Error();
        error.setErrorCode(String.valueOf(HttpStatus.FORBIDDEN.value()));
        error.setErrorMessage(exception.getMessage());
        return ResponseEntity.ok(error);
    }

}
