package com.shashi.foodOrdering.controller;

import com.shashi.foodOrdering.dto.OrderRequest;
import com.shashi.foodOrdering.entity.Order;
import com.shashi.foodOrdering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody OrderRequest request){
        System.out.println(request.getUserId() + " => " + request.getRestaurantId());
        return orderService.placeOrder(request);
    }
}