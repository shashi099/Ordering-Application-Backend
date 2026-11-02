package com.shashi.foodOrdering.dto;

import com.shashi.foodOrdering.entity.Order;
import com.shashi.foodOrdering.entity.OrderItem;
import com.shashi.foodOrdering.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsResponse {

    private Long orderId;
    private String restaurantName;
    private Double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;

    private List<OrderItemResponse> orderItemList;

}
