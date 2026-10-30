package com.shashi.foodOrdering.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuItemResponse {

    private Long menuItemId;
    private String foodName;
    private String description;
    private Double price;
    private Boolean available;

    private Long restaurantId;
    private String restaurantName;
}
