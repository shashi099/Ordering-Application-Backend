package com.shashi.foodOrdering.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {

    private String foodName;

    private Integer quantity;

    private Double price;

    private Double subTotal;
}