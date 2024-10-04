package com.employeemanagementsystem.service;

import com.employeemanagementsystem.errorhandling.UserAlreadyExistsException;
import com.employeemanagementsystem.model.User;
import com.employeemanagementsystem.model.UserDTO;
import com.employeemanagementsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User is not found in Employee Repository");
        }
    }

    public void saveUser(UserDTO dto) {
        Optional.ofNullable(dto)
               // .map(userDTO -> userDTO.getUsername().toUpperCase())
                .filter(username -> !this.findByUsername(username.getUsername())) // Only proceed if the user doesn't exist
                .orElseThrow(() -> new UserAlreadyExistsException("Username already exists, try using another username"));

        User entity = Optional.of(dto)
                .map(userDto -> {
                    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                    User user = new User();
                    BeanUtils.copyProperties(userDto, user);
                    return user;
                })
                .orElseThrow(() -> new RuntimeException("Failed to map UserDTO to User entity"));

        userRepository.save(entity);
    }

    public boolean findByUsername(String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.isPresent();
    }
}

