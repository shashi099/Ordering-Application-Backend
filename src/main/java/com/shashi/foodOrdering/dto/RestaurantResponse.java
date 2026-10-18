package com.shashi.foodOrdering.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class RestaurantResponse {

    private Long restaurant_id;
    private String restaurant_name;
    private String restaurant_address;
    private Double restaurant_rating;
    private String restaurant_image;

}
