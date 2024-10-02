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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public @NotBlank(message = "firstName cannot be null") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "firstName cannot be null") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "lastName cannot be null") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "lastName cannot be null") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "email cannot be null") @Email(message = "Enter valid email address") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "email cannot be null") @Email(message = "Enter valid email address") String email) {
        this.email = email;
    }

    public @NotNull(message = "department cannot be null") String getDepartment() {
        return department;
    }

    public void setDepartment(@NotNull(message = "department cannot be null") String department) {
        this.department = department;
    }

    public @NotNull(message = "salary cannot be null") @Range(min = 1000, message = " Invalid Salary Entered") Double getSalary() {
        return salary;
    }

    public void setSalary(@NotNull(message = "salary cannot be null") @Range(min = 1000, message = " Invalid Salary Entered") Double salary) {
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @NotNull(message = "password cannot be null") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "password cannot be null") String password) {
        this.password = password;
    }
}

