package com.employeemanagementsystem.service;

import com.employeemanagementsystem.model.User;
import com.employeemanagementsystem.model.UserDTO;
import com.employeemanagementsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        logger.info(" load user byusername: " + user);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    user.get().getUsername(), user.get().getPassword(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User is not found in Employee Repository");
        }
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

