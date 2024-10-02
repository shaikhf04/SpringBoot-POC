package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.errorHandling.EmployeeNotFoundException;
import com.employeemanagementsystem.repository.UserRepository;
import com.employeemanagementsystem.request.AuthenticationRequest;
import com.employeemanagementsystem.response.AuthenticationResponse;
import com.employeemanagementsystem.service.EmployeeService;
import com.employeemanagementsystem.service.UserService;
import com.employeemanagementsystem.utility.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("This is a test endpoint!");
    }

    @PostMapping("/authenticate/getToken")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
            String jwt= "";
            try {
                authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
                UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
                jwt = jwtTokenUtil.generateToken(userDetails);
            }catch (Exception e){
                throw new EmployeeNotFoundException("Employee Record Not Found id: " + authenticationRequest.getUsername());
            }
            AuthenticationResponse response = new AuthenticationResponse(jwt);
        return ResponseEntity.ok(response);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
    }

}

