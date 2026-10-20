package com.shashi.foodOrdering.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuItemResponse {

    private Long menuId;
    private String item_name;
    private String item_description;
    private Double item_price;
    private Boolean item_available;

    private Long restaurantId;
    private String restaurantName;
}
