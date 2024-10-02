package com.employeemanagementsystem.utility;

import com.employeemanagementsystem.service.EmployeeService;

import com.employeemanagementsystem.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    @Lazy
    UserService userService;

    public JwtRequestFilter(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService= userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public JwtRequestFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtTokenUtil.extractUsername(jwt);
            if(username.isEmpty())
                throw new UsernameNotFoundException("Username Not Found Exception");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwt, userDetails.getUsername())) {
                // Set the authentication details in the security context
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}

