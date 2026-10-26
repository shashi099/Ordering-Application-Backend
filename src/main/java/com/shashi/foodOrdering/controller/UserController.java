package com.shashi.foodOrdering.controller;

import com.shashi.foodOrdering.dto.ApiResponse;
import com.shashi.foodOrdering.dto.LoginRequest;
import com.shashi.foodOrdering.dto.UserRegistrationRequest;
import com.shashi.foodOrdering.dto.UserResponse;
import com.shashi.foodOrdering.entity.User;
import com.shashi.foodOrdering.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        System.out.println("Inside the controller " + request);

        UserResponse response = userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                                .body(
                                        ApiResponse.<UserResponse>builder()
                                                .success(true)
                                                .message("User Registered Successfully")
                                                .data(response)
                                                .build()
                                );

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(
            @Valid
            @RequestBody LoginRequest request) {

        System.out.println("Shashi singh data is coming");

        // Assuming userService.login(request) returns the authenticated User entity/DTO
        User loggedInUser = userService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<User>builder()
                        .success(true)
                        .message("User Login- Successful")
                        .data(loggedInUser) // <--- Pass the actual user object here!
                        .build()
        );
    }



}




