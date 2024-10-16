package com.employeemanagementsystem.service;

import com.employeemanagementsystem.errorhandling.EmployeeNotFoundException;
import com.employeemanagementsystem.errorhandling.RecordDoesNotExistException;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public Employee createNewEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployee(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Page<Employee> getAllEmployees(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        System.out.println("Page: "+page+"Size: "+size );
        return employeeRepository.findAll(pageRequest);
    }

    public Employee updateEmployees(Employee employee, Integer id) throws EmployeeNotFoundException {
        Optional<Employee> existEmployee = employeeRepository.findById(id);
        //Check if object exists already then update it
        if (existEmployee.isPresent()) {
            Employee employee1 = existEmployee.get();
            employee1.setFirstName(employee.getFirstName());
            employee1.setLastName(employee.getLastName());
            employee1.setEmail(employee.getEmail());
            employee1.setDepartment(employee.getDepartment());
            employee1.setSalary(employee.getSalary());
            employee1.setPassword(passwordEncoder.encode(employee.getPassword()));
            return employeeRepository.save(employee1);
        } else {// Custom Exception using ControllerAdvice
            throw new EmployeeNotFoundException("Employee Record Not Found id: " + id);
        }
    }

    public void deleteEmployee(Integer id) throws RecordDoesNotExistException {
        Optional<Employee> existEmployee = employeeRepository.findById(id);
        if (existEmployee.isPresent()) {
            employeeRepository.deleteById(id);
        } else {// Custom Exception using ControllerAdvice
            throw new RecordDoesNotExistException("Employee Record Does Not Exist id: " + id);
        }
    }

}
