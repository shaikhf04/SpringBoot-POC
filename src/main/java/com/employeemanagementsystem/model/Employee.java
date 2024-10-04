package com.employeemanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeId;

    @NotBlank(message = "firstName cannot be null")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    private String lastName;

    @NotNull(message = "username cannot be null")
    private String username;

    @NotNull(message = "email cannot be null")
    @Email(message = "Enter valid email address")
    private String email;

    @NotNull(message = "department cannot be null")
    private String department;

    @NotNull(message = "salary cannot be null")
    @Range( min = 1000 , message = " Invalid Salary Entered")
    private Double salary;

    @NotNull(message = "password cannot be null")
    private String password;
}

