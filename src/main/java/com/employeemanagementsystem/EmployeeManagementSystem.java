package com.employeemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication(scanBasePackages = "com.employeemanagementsystem")
//@PropertySource(value = "classpath:application.yaml")
public class EmployeeManagementSystem {
    public static void main(String[] args) {
         SpringApplication.run(EmployeeManagementSystem.class,args);
    }
}