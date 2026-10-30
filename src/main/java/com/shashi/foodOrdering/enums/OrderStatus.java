package com.shashi.foodOrdering.enums;

import jakarta.persistence.Enumerated;


public enum OrderStatus {
    PLACED,

    PREPARING,

    PICKED_UP,

    DELIVERED,

    PENDING, CANCELLED
}
