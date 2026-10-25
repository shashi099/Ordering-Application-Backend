package com.shashi.foodOrdering.dto;

import com.shashi.foodOrdering.entity.MenuItem;
import com.shashi.foodOrdering.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddToCartResponse {

    private Long cartId;

    private int quantity;

    private User user;

    private MenuItem menuItem;

    @Override
    public String toString() {
        return "AddToCartResponse{" +
                "cartId=" + cartId +
                ", quantity=" + quantity +
                ", user=" + user +
                ", menuItem=" + menuItem +
                '}';
    }
}
