package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.errorHandling.EmployeeNotFoundException;
import com.employeemanagementsystem.errorHandling.RecordDoesNotExistException;
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
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }

    public Employee createValues() {
        Employee employeeMock = new Employee();
        employeeMock.setFirstName("first");
        employeeMock.setLastName("last");
        employeeMock.setEmail("abc@xaz.occ");
        employeeMock.setDepartment("aas");
        employeeMock.setSalary(1000.0);
        return employeeMock;
    }

    public Employee setValues() {
        Employee employeeMock = new Employee();
        employeeMock.setEmployeeId(1);
        employeeMock.setFirstName("first");
        employeeMock.setLastName("last");
        employeeMock.setEmail("rrr@zzz.ttt");
        employeeMock.setDepartment("aaa");
        employeeMock.setSalary(2000.0);
        return employeeMock;
    }

    public Employee setValues2() {
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
    public void createNewEmp() throws Exception {
        when(employeeService.createNewEmployee(any())).thenReturn(createValues());
        ResponseEntity<Employee> responseEntity = employeeController.createEmployee(createValues());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        verify(employeeService, times(1)).createNewEmployee(any(Employee.class));
    }

    @Test
    public void testGetEmployee_IfExists() throws Exception {
        when(employeeService.getEmployee(1)).thenReturn(Optional.of(setValues()));
        ResponseEntity<Optional<Employee>> responseEntity = employeeController.getEmployee(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetEmployee_IfNotExists() throws Exception {
        when(employeeService.getEmployee(1)).thenReturn(Optional.empty());
        ResponseEntity<Optional<Employee>> responseEntity = employeeController.getEmployee(1);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void updateEmployee_Test() throws EmployeeNotFoundException {
        when(employeeService.updateEmployees(setValues(), 1)).thenReturn(setValues());
            ResponseEntity<Employee> responseEntity=  employeeController.updateEmployee(setValues(),1);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateEmployee_ThrowException() throws EmployeeNotFoundException {
            when(employeeService.updateEmployees(setValues(), 1)).thenThrow(EmployeeNotFoundException.class);
            assertThrows(EmployeeNotFoundException.class, () -> {
                ResponseEntity<Employee> responseEntity=  employeeController.updateEmployee(setValues(),1);
                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            });
    }

    @Test
    public void getAllEmployees() throws Exception {
        List<Employee> employeeList = List.of(setValues(), setValues2());
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        ResponseEntity<List<Employee>> listResponseEntity = employeeController.getAllEmployees();
        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
    }

    @Test
    public void updateEmployee_Error() throws Exception {
        when(employeeService.updateEmployees(any(),any())).thenThrow(EmployeeNotFoundException.class);
        assertThrows(EmployeeNotFoundException.class, () -> {
            ResponseEntity<Employee> updatedEmployee = employeeController.updateEmployee(setValues(), 1);
            assertEquals(HttpStatus.OK,updatedEmployee.getStatusCode());
        });
    }

    @Test
    public void deleteEmployee_IfExists() throws Exception {
        ResponseEntity<Object> objectResponseEntity = employeeController.deleteEmployee(1);
        assertEquals(HttpStatus.OK, objectResponseEntity.getStatusCode());
    }

    @Test
    public void deleteEmployee_Exception_IfNotExist() throws RecordDoesNotExistException {
         doThrow(new RecordDoesNotExistException("Record Not Exists")).when(employeeService).deleteEmployee(any());
         assertThrows(RecordDoesNotExistException.class, () -> {
             ResponseEntity<Object> objectResponseEntity = employeeController.deleteEmployee(any());
             assertEquals(HttpStatus.OK, objectResponseEntity.getStatusCode());
         });
    }
}




