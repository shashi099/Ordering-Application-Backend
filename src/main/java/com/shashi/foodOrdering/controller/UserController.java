package com.shashi.foodOrdering.controller;

import com.shashi.foodOrdering.dto.LoginRequest;
import com.shashi.foodOrdering.dto.UserRegistrationRequest;
import com.shashi.foodOrdering.dto.UserResponse;
import com.shashi.foodOrdering.entity.User;
import com.shashi.foodOrdering.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        System.out.println("Inside the controller " + request);

        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public String login(
            @Valid
            @RequestBody LoginRequest request) {
        System.out.println("Shashi singh");
        return userService.login(request);
    }



}




