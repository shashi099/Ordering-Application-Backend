package com.shashi.foodOrdering.controller;


import com.shashi.foodOrdering.dto.AddToCartRequest;
import com.shashi.foodOrdering.dto.AddToCartResponse;
import com.shashi.foodOrdering.dto.ApiResponse;
import com.shashi.foodOrdering.entity.Cart;
import com.shashi.foodOrdering.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/add")
    public String hello(){
        System.out.println("hello bruh@@");
        return "hello ji";
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<AddToCartResponse>> addToCart(
            @Valid @RequestBody AddToCartRequest request) {

        System.out.println("Kya huaa bhai");

        AddToCartResponse response = cartService.addToCart(request);

        System.out.println(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<AddToCartResponse>builder()
                        .success(true)
                        .message("Quantity Updated Successfully")
                        .data(response)
                        .build());
    }
}