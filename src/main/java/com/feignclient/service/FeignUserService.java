package com.feignclient.service;

import com.feignclient.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserServiceClient userServiceClient;
    public UserService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    public User getUser(Long id) {
        return userServiceClient.getUserById(id);
    }
}
