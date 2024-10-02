/*
package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.errorHandling.InvalidPayloadException;
import com.employeemanagementsystem.errorHandling.UserAlreadyExistsException;
import com.employeemanagementsystem.model.UserDTO;
import com.employeemanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (Objects.isNull(userDTO)) {
            throw new InvalidPayloadException("Payload cannot be Null");
        }
        if(userService.findByUsername(userDTO.getUsername())){
            throw new UserAlreadyExistsException("Username name already exists, Try using another username ");
        }
        userService.saveUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }
}
*/
