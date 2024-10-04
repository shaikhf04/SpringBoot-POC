package com.employeemanagementsystem.errorHandling;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String employeeNotFound) {
        super(employeeNotFound);
    }
}
