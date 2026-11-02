package com.shashi.foodOrdering.repository;

import com.shashi.foodOrdering.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {



}
