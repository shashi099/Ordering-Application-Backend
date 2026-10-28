package com.shashi.foodOrdering.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuantityRequest {
    Integer quantity;
}
