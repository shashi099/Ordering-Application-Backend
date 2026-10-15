package com.shashi.foodOrdering.controller;

import com.shashi.foodOrdering.dto.UserRegistrationRequest;
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
    public User registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        return userService.registerUser(request);
    }
}




