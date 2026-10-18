package com.shashi.foodOrdering.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequest {

    @NotBlank(message = "Restaurant name Required")
    private String name;
    @NotBlank
    private String restaurant_address;

    private String restaurant_image;

    private Double restaurant_rating;

    @Override
    public String toString() {
        return "RestaurantRequest{" +
                "name='" + name + '\'' +
                ", restaurant_address='" + restaurant_address + '\'' +
                ", restaurant_image='" + restaurant_image + '\'' +
                '}';
    }
}
