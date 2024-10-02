package com.employeemanagementsystem.service;

import com.employeemanagementsystem.model.User;
import com.employeemanagementsystem.model.UserDTO;
import com.employeemanagementsystem.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDTO dto) {
        User entity = new User();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        BeanUtils.copyProperties(dto, entity);
        User savedUser = userRepository.save(entity);
        dto.setPassword("******");
    }

    public boolean findByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if(byUsername.isPresent()) {
            return true;
        }
        return false;
    }
}

