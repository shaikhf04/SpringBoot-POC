package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@Tag(name = "EmployeeController", description = "The Employee API")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Create Employee",
            description = "Create a new Employee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Record Created!"),
            }
    )
    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee newEmployee = employeeService.createNewEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }


    @GetMapping("/employee/{id}")
    @Operation(
            summary = "Get employee by ID",
            description = "Get an employee's details by their ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee found"),
                    @ApiResponse(responseCode = "404", description = "Employee not found")
            }
    )
    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (employee.isPresent())
            return new ResponseEntity<>(employee, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employee/all")
    @Operation(
            summary = "Get all employees",
            description = "Retrieve a list of all employees from the system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


    @Operation(
            summary = "Update employee by ID",
            description = "Update Employee's details by their ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee found"),
                    @ApiResponse(responseCode = "404", description = "Employee not found")
            }
    )
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable Integer id) {
        Employee updatedEmployee = employeeService.updateEmployees(employee, id);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }


    @Operation(
            summary = "Delete employee by ID",
            description = "Delete Employee by their ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee deleted"),
                    @ApiResponse(responseCode = "404", description = "Employee not found")
            }
    )
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee Record deleted successfully");
    }

}
