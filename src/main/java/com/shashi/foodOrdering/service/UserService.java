package com.shashi.foodOrdering.service;

import com.shashi.foodOrdering.config.SecurityConfig;
import com.shashi.foodOrdering.dto.LoginRequest;
import com.shashi.foodOrdering.dto.UserRegistrationRequest;
import com.shashi.foodOrdering.dto.UserResponse;
import com.shashi.foodOrdering.entity.User;
import com.shashi.foodOrdering.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // save the registration into the db
    public UserResponse registerUser(UserRegistrationRequest request) {

        if (userRepository
                .existsByEmail(request.getEmail())) {

            throw new RuntimeException(
                    "Already User Exist");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        user.setPhone(request.getPhone());

//        System.out.println("Inside the service " + user );

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .build();
    }

    // login the user
    public String login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean isValidPassword =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!isValidPassword) {
            throw new RuntimeException("Invalid Password");
        }

        return "Login Successful";
    }





}