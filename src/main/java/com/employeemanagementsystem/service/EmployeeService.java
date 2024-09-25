package com.employeemanagementsystem.service;

import com.employeemanagementsystem.errorHandling.EmployeeNotFoundException;
import com.employeemanagementsystem.errorHandling.RecordDoesNotExistException;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    Logger logger= LoggerFactory.getLogger(EmployeeService.class);

    /*
    *   - `POST /employees` – Create a new employee.
        - `GET /employees/{id}` – Retrieve an employee by ID.
        - `PUT /employees/{id}` – Update an employee’s information.
        - `DELETE /employees/{id}` – Delete an employee by ID.
        - `GET /employees` – Retrieve all employees.
     */

    public Employee createNewEmployee(Employee employee){
        logger.info("Record Added!");
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

    public Employee updateEmployees(Employee employee, Integer id) {
        logger.info("Record Updated !");
        Optional<Employee> existEmployee = employeeRepository.findById(id);
        //Check if object exists already then update it
        if(existEmployee.isPresent()){
            Employee employee1= existEmployee.get();
            employee1.setFirstName(employee.getFirstName());
            employee1.setLastName(employee.getLastName());
            employee1.setEmail(employee.getEmail());
            employee1.setDepartment(employee.getDepartment());
            employee1.setSalary(employee.getSalary());
            return employeeRepository.save(employee1);
        }
        else{// Custom Exception using ControllerAdvice
            throw new EmployeeNotFoundException("Employee Record Not Found id: " + id);
        }
    }

    public void deleteEmployee(Integer id) {
        logger.info("Records deleted!");
        Optional<Employee> existEmployee = employeeRepository.findById(id);
        if(existEmployee.isPresent()) {
           employeeRepository.deleteById(id);
        }
        else{// Custom Exception using ControllerAdvice
            throw new RecordDoesNotExistException("Employee Record Does Not Exist id: " + id);
        }
    }
}