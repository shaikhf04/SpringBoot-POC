package com.employeemanagementsystem.errorhandling;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String employeeNotFound) {
        super(employeeNotFound);
    }
}
