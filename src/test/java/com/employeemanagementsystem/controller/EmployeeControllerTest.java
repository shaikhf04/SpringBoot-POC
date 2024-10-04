/*
package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.errorhandling.EmployeeNotFoundException;
import com.employeemanagementsystem.errorhandling.RecordDoesNotExistException;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.repository.EmployeeRepository;
import com.employeemanagementsystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @Autowired
    MockMvc mvc;

    @Mock
    EmployeeService employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

    Employee employee;

    ObjectMapper objectMapper;

    @BeforeEach
     void setup() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }

     Employee createValues() {
        Employee employeeMock = new Employee();
        employeeMock.setFirstName("first");
        employeeMock.setLastName("last");
        employeeMock.setEmail("abc@xaz.occ");
        employeeMock.setDepartment("aas");
        employeeMock.setSalary(1000.0);
        return employeeMock;
    }

     Employee setValues() {
        Employee employeeMock = new Employee();
        employeeMock.setEmployeeId(1);
        employeeMock.setFirstName("first");
        employeeMock.setLastName("last");
        employeeMock.setEmail("rrr@zzz.ttt");
        employeeMock.setDepartment("aaa");
        employeeMock.setSalary(2000.0);
        return employeeMock;
    }

     Employee setValues2() {
        Employee employeeMock = new Employee();
        employeeMock.setEmployeeId(2);
        employeeMock.setFirstName("second");
        employeeMock.setLastName("last");
        employeeMock.setEmail("www@xasd.occ");
        employeeMock.setDepartment("ee");
        employeeMock.setSalary(2000.0);
        return employeeMock;
    }

    @Test
    void createNewEmp() {
        when(employeeService.createNewEmployee(any())).thenReturn(createValues());
        ResponseEntity<Employee> responseEntity = employeeController.createEmployee(createValues());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(employeeService, times(1)).createNewEmployee(any(Employee.class));
    }

    @Test
     void testGetEmployee_IfExists() {
        when(employeeService.getEmployee(1)).thenReturn(Optional.of(setValues()));
        ResponseEntity<Optional<Employee>> responseEntity = employeeController.getEmployee(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
     void testGetEmployee_IfNotExists() {
        when(employeeService.getEmployee(1)).thenReturn(Optional.empty());
        ResponseEntity<Optional<Employee>> responseEntity = employeeController.getEmployee(1);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
     void updateEmployee_Test() throws EmployeeNotFoundException {
        when(employeeService.updateEmployees(setValues(), 1)).thenReturn(setValues());
            ResponseEntity<Employee> responseEntity=  employeeController.updateEmployee(setValues(),1);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
     void updateEmployee_ThrowException() throws EmployeeNotFoundException {
            when(employeeService.updateEmployees(setValues(), 1)).thenThrow(EmployeeNotFoundException.class);
            assertThrows(EmployeeNotFoundException.class, () -> {
                ResponseEntity<Employee> responseEntity=  employeeController.updateEmployee(setValues(),1);
                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            });
    }

    @Test
     void getAllEmployees() {
        List<Employee> employeeList = List.of(setValues(), setValues2());
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        ResponseEntity<List<Employee>> listResponseEntity = employeeController.getAllEmployees();
        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
    }

    @Test
     void updateEmployee_Error()  {
        when(employeeService.updateEmployees(any(),any())).thenThrow(EmployeeNotFoundException.class);
        assertThrows(EmployeeNotFoundException.class, () -> {
            ResponseEntity<Employee> updatedEmployee = employeeController.updateEmployee(setValues(), 1);
            assertEquals(HttpStatus.OK,updatedEmployee.getStatusCode());
        });
    }

    @Test
     void deleteEmployee_IfExists()  {
        ResponseEntity<Object> objectResponseEntity = employeeController.deleteEmployee(1);
        assertEquals(HttpStatus.OK, objectResponseEntity.getStatusCode());
    }

    @Test
     void deleteEmployee_Exception_IfNotExist() throws RecordDoesNotExistException {
         doThrow(new RecordDoesNotExistException("Record Not Exists")).when(employeeService).deleteEmployee(any());
         assertThrows(RecordDoesNotExistException.class, () -> {
             ResponseEntity<Object> objectResponseEntity = employeeController.deleteEmployee(any());
             assertEquals(HttpStatus.OK, objectResponseEntity.getStatusCode());
         });
    }
}




*/
