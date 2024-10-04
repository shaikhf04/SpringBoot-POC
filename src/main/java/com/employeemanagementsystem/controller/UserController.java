package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.errorhandling.InvalidPayloadException;
import com.employeemanagementsystem.errorhandling.UserAlreadyExistsException;
import com.employeemanagementsystem.model.UserDTO;
import com.employeemanagementsystem.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (userDTO==null) {
            throw new InvalidPayloadException("Payload cannot be Null");
        }
        if(userService.findByUsername(userDTO.getUsername())){
            throw new UserAlreadyExistsException("Username name already exists, Try using another username ");
        }
        if(userDTO.getUsername()!=null && userDTO.getPassword()!=null){
            userService.saveUser(userDTO);
        }

        return ResponseEntity.ok("User registered successfully. Welcome "+ userDTO.getUsername());
    }
}
