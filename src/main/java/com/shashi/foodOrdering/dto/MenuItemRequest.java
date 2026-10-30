package com.shashi.foodOrdering.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemRequest {

    @NotBlank(message = "Item name Required")
    private String foodName;
    private String description;
    @NotNull
    private Double price;
    private Boolean available;

    @Override
    public String toString() {
        return "MenuItemRequest{" +
                "foodName='" + foodName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
