package com.shashi.foodOrdering.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class RestaurantResponse {

    private Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private Double restaurantRating;
    private String restaurantImage;

}
