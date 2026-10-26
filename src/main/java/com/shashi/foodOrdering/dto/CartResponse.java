package com.shashi.foodOrdering.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private List<CartItemResponse> items;

    private Double totalAmount;

}
    /*  We send the data from server to client in this format
    {
  "data": [
    {
      "foodName": "Burger",
      "price": 120,
      "quantity": 2
    },
    {
      "foodName": "Pizza",
      "price": 250,
      "quantity": 1
    }
  ],
  "totalAmount": 490
}
     */
