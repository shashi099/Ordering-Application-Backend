package com.shashi.foodOrdering.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
