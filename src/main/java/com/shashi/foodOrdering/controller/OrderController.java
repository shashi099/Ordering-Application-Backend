package com.shashi.foodOrdering.controller;

import com.shashi.foodOrdering.dto.ApiResponse;
import com.shashi.foodOrdering.dto.OrderDetailsResponse;
import com.shashi.foodOrdering.dto.OrderRequest;
import com.shashi.foodOrdering.dto.OrderResponse;
import com.shashi.foodOrdering.entity.Order;
import com.shashi.foodOrdering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<ApiResponse<OrderResponse>> placeOrder(@RequestBody OrderRequest request){
        System.out.println(request.getUserId() + " => " );

        OrderResponse response = orderService.placeOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<OrderResponse>builder()
                        .success(true)
                        .message("Order placed Successfully")
                        .data(response)
                        .build()
        );

    }

    // Get All the Orders of A User
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getUserOrders(@PathVariable Long userId){

        List<OrderResponse> responses = orderService.getUserOrders(userId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<OrderResponse>>builder()
                        .success(true)
                        .message("All the user Orders")
                        .data(responses)
                        .build()
        );
    }

    // Get Details of a Particular order
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderDetailsResponse>> getOrderDetails(@PathVariable Long orderId){

        OrderDetailsResponse orderDetails = orderService.getOrderDetails(orderId);

        System.out.println(orderDetails);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<OrderDetailsResponse>builder()
                        .success(true)
                        .message("All the Order Details")
                        .data(orderDetails)
                        .build()
        );
    }
}