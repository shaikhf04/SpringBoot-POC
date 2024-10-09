package com.employeemanagementsystem.configuration;

import com.employeemanagementsystem.utility.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final JwtRequestFilter jwtRequestFilter;

    private static final String[] publicEndpoints = {
            "/test",
            "/authenticate/login",
            "/authenticate/logout",
            "/authenticate/refreshToken",
            "/user/register",
            "/h2-console/**",
            "/swagger/**",
            "/v3/api-docs/swagger-config",
            "/v3/api-docs",
            "/actuator/*"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/**"));
        httpSecurity.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        httpSecurity.authorizeHttpRequests(requests -> requests
                    .requestMatchers(publicEndpoints).permitAll()
                .anyRequest().authenticated()); //Any other request will be restricted
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
        return httpSecurity.build();
    }

}
