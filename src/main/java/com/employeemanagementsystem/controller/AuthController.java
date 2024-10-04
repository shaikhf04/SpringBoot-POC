package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.request.AuthenticationRequest;
import com.employeemanagementsystem.response.AuthenticationResponse;
import com.employeemanagementsystem.service.UserService;
import com.employeemanagementsystem.utility.JwtTokenUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AuthController", description = "The Authorization API")
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("This is a test endpoint!");
    }

    @PostMapping("/authenticate/refreshToken")
    public ResponseEntity<AuthenticationResponse> generateRefreshToken(@RequestHeader("Authorization") String refreshToken) {

        if(refreshToken==null || refreshToken.isEmpty() || refreshToken.startsWith("Bear "))
            return null;
        String authorizationHeader = refreshToken.substring(7);
        //Authenticate- Verify user exists or not
        String userNameFromToken = jwtTokenUtil.extractUsername(authorizationHeader);
        UserDetails userDetails = userService.loadUserByUsername(userNameFromToken);
        String jwt = jwtTokenUtil.generateToken(userDetails);
        refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse(jwt,refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate/getToken")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)  {
            String jwt= "";
            String newRefreshToken="";
                authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
                UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
                jwt = jwtTokenUtil.generateToken(userDetails);
                newRefreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

                AuthenticationResponse response = new AuthenticationResponse(jwt, newRefreshToken);
            return ResponseEntity.ok(response);
    }

    private void authenticate(String username, String password) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}

