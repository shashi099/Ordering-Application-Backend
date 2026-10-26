package com.shashi.foodOrdering.service;

import com.shashi.foodOrdering.config.SecurityConfig;
import com.shashi.foodOrdering.dto.LoginRequest;
import com.shashi.foodOrdering.dto.UserRegistrationRequest;
import com.shashi.foodOrdering.dto.UserResponse;
import com.shashi.foodOrdering.entity.User;
import com.shashi.foodOrdering.exception.InvalidCredentialsException;
import com.shashi.foodOrdering.exception.UserAlreadyExistsException;
import com.shashi.foodOrdering.exception.UserNotFoundException;
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

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new UserAlreadyExistsException("Email Already Registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
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
    public User login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException("User not Found"));

        boolean isValidPassword =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!isValidPassword) {
            throw new InvalidCredentialsException("Invalid Email or Password");
        }

        return user;
    }





}