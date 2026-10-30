package com.shashi.foodOrdering.repository;

import com.shashi.foodOrdering.entity.Order;
import com.shashi.foodOrdering.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

}
