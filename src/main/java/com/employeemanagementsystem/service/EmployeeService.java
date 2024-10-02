package com.employeemanagementsystem.service;

import com.employeemanagementsystem.errorHandling.EmployeeNotFoundException;
import com.employeemanagementsystem.errorHandling.RecordDoesNotExistException;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {//implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private Integer id;
    private String username;
    private String password;

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee createNewEmployee(Employee employee) {
        logger.info("Record Added!");
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployee(Integer employeeId) {
        logger.info("Get Employee Record by ID!");
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getAllEmployees() {
        logger.info("All records!");
        return employeeRepository.findAll();
    }

    public Employee updateEmployees(Employee employee, Integer id) throws EmployeeNotFoundException {
        logger.info("Record Updated !");
        Optional<Employee> existEmployee = employeeRepository.findById(id);
        //Check if object exists already then update it
        if (existEmployee.isPresent()) {
            Employee employee1 = existEmployee.get();
            employee1.setFirstName(employee.getFirstName());
            employee1.setLastName(employee.getLastName());
            employee1.setEmail(employee.getEmail());
            employee1.setDepartment(employee.getDepartment());
            employee1.setSalary(employee.getSalary());
            employee1.setPassword(passwordEncoder.encode(getPassword()));
            return employeeRepository.save(employee1);
        } else {// Custom Exception using ControllerAdvice
            throw new EmployeeNotFoundException("Employee Record Not Found id: " + id);
        }
    }

    public void deleteEmployee(Integer id) throws RecordDoesNotExistException {
        logger.info("Records deleted!");
        Optional<Employee> existEmployee = employeeRepository.findById(id);
        if (existEmployee.isPresent()) {
            employeeRepository.deleteById(id);
        } else {// Custom Exception using ControllerAdvice
            throw new RecordDoesNotExistException("Employee Record Does Not Exist id: " + id);
        }
    }

}
