package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.model.UserDTO;
import com.employeemanagementsystem.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "The User API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserDTO userDTO) {
            userService.saveUser(userDTO);
        return ResponseEntity.ok("User registered successfully. Welcome " + userDTO.getUsername());
    }
}
