package com.employeemanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @NotNull(message = "User Name cannot be null")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "email cannot be null")
    private String email;

    public User() {
    }

    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public @NotNull(message = "User Name cannot be null") String getUsername() {
        return username;
    }

    public void setUsername(@NotNull(message = "User Name cannot be null") String username) {
        this.username = username;
    }

    public @NotNull(message = "Password cannot be null") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Password cannot be null") String password) {
        this.password = password;
    }

    public @NotNull(message = "email cannot be null") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "email cannot be null") String email) {
        this.email = email;
    }

}

