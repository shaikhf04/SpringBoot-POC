package com.employeemanagementsystem.response;

public class AuthenticationResponse {

    private final String jwt;
    private final String refreshJWTToken;


    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
        refreshJWTToken = "";
    }

    public AuthenticationResponse(String jwt, String refreshJWTToken) {
        this.jwt = jwt;
        this.refreshJWTToken = refreshJWTToken;
    }

    public String getJwt() {
        return jwt;
    }

    public String getRefreshJWTToken() {
        return refreshJWTToken;
    }
}

