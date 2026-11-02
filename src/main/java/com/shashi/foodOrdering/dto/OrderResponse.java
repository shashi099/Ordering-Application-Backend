package com.shashi.foodOrdering.dto;

import com.shashi.foodOrdering.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {

    private Long orderId;

    private String restaurantName;

    private Double totalAmount;

    private OrderStatus status;

    private LocalDateTime createdAt;

}