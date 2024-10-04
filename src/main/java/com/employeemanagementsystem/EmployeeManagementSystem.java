package com.employeemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication (scanBasePackages = {"com.employeemanagementsystem"})

public class EmployeeManagementSystem {
    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(EmployeeManagementSystem.class,args);
        System.out.println(applicationContext.toString());
    }
}