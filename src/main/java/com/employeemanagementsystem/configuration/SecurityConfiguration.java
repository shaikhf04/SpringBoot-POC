package com.employeemanagementsystem.configuration;

import com.employeemanagementsystem.service.UserService;
import com.employeemanagementsystem.utility.JwtRequestFilter;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
@EnableMethodSecurity
@Configuration
public class SecurityConfiguration {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    String[] publicEndpoints = {
            "/test",
            "/authenticate/login",
            "/authenticate/refreshToken",
            "/user/register",
            "/h2-console/**",
            "/swagger/**",
            "/v3/api-docs/swagger-config",
            "/v3/api-docs"
    };

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
        httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/**"));
        httpSecurity.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        httpSecurity.authorizeHttpRequests(requests -> requests
                .requestMatchers(publicEndpoints).permitAll()
//                .requestMatchers("/test", "/user/register", "/authenticate/**").permitAll()
//                .requestMatchers("/h2-console**/**", "/swagge**/**", "/v3/api-docs/swagger-config", "/v3/api-docs").permitAll()
//                .requestMatchers("/authenticate/login").permitAll() // Allow public access to token generation
                .anyRequest().authenticated()); //Any other request will be restricted
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return httpSecurity.build();
    }

}
