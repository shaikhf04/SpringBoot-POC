package com.employeemanagementsystem.config;

import com.employeemanagementsystem.model.User;
import com.employeemanagementsystem.service.EmployeeService;
import com.employeemanagementsystem.service.UserService;
import com.employeemanagementsystem.utility.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
public class Config {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtRequestFilter jwtAuthenticationFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);  // Ensure BCrypt is used here
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/**") );
        httpSecurity.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        httpSecurity.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/test","/user/register","/authenticate/getToken").permitAll()
                .requestMatchers("/h2-console**/**").permitAll()
                .requestMatchers("/authenticate/login").permitAll() // Allow public access to token generation
                .anyRequest().authenticated()); //Any other request will be restricted
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return httpSecurity.build();
    }

}
