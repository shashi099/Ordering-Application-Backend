package com.shashi.foodOrdering.controller;


import com.shashi.foodOrdering.dto.*;
import com.shashi.foodOrdering.entity.Cart;
import com.shashi.foodOrdering.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<AddToCartResponse>> addToCart(
            @Valid @RequestBody AddToCartRequest request) {

        System.out.println("Kya huaa bhai");

        AddToCartResponse response = cartService.addToCart(request);

        System.out.println(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<AddToCartResponse>builder()
                        .success(true)
                        .message("Item Added To Cart Successfully")
                        .data(response)
                        .build());
    }

    // All Cart Items of a User
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<CartResponse>> getUserCart(@PathVariable Long userId){

        CartResponse response = cartService.getUserCartItems(userId);

        System.out.println("Kuch mila " + response.getTotalAmount());

        return ResponseEntity.ok(
                ApiResponse.<CartResponse>builder()
                        .success(true)
                        .message("All Carts got here")
                        .data(response)
                        .build()
        );
    }

    // Update the Cart
    @PutMapping("/update/{cartId}")
    public ResponseEntity<ApiResponse<Cart>> updateCartQuantity(
            @PathVariable Long cartId,
            @RequestBody UpdateQuantityRequest request){

        System.out.println("Pahuch paye ho");

        Cart cart = cartService.updateCartQuantity(cartId, request);

        System.out.println("Are you here or not");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                ApiResponse.<Cart>builder()
                        .success(true)
                        .message("Cart Updated Successfully")
                        .data(cart)
                        .build()
        );
    }

}