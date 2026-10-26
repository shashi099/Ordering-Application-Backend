package com.shashi.foodOrdering.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {

    private Long menuItemId;

    private String foodName;

    private Double price;

    private Integer quantity;

    private Double subTotal;

}
