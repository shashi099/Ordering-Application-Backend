package com.shashi.foodOrdering.service;

import com.shashi.foodOrdering.dto.UserRegistrationRequest;
import com.shashi.foodOrdering.entity.User;
import com.shashi.foodOrdering.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserRegistrationRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        return userRepository.save(user);
    }
}