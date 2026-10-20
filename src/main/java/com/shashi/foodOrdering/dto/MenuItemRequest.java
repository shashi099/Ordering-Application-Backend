package com.shashi.foodOrdering.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemRequest {

    @NotBlank(message = "Item name Required")
    private String item_name;
    private String item_description;
    @NotNull
    private Double item_price;
    private Boolean item_available;

    @Override
    public String toString() {
        return "MenuItemRequest{" +
                "item_name='" + item_name + '\'' +
                ", item_description='" + item_description + '\'' +
                ", item_price=" + item_price +
                ", item_available=" + item_available +
                '}';
    }
}
