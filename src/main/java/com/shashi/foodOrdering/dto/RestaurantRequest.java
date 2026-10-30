package com.shashi.foodOrdering.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequest {

    @NotBlank(message = "Restaurant name Required")
    private String restaurantName;
    @NotBlank
    private String restaurantAddress;

    private String restaurantImage;

    private Double restaurantRating;

    @Override
    public String toString() {
        return "RestaurantRequest{" +
                "name='" + restaurantName + '\'' +
                ", restaurantAddress='" + restaurantAddress + '\'' +
                ", restaurantImage='" + restaurantImage + '\'' +
                ", restaurantRating=" + restaurantRating +
                '}';
    }
}
