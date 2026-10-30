package com.shashi.foodOrdering.repository;

import com.shashi.foodOrdering.entity.Cart;
import com.shashi.foodOrdering.entity.MenuItem;
import com.shashi.foodOrdering.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {


    //Finding cart items of a user
//    List<Cart> findByUser(User user);
    List<Cart> findByUserOrderByCartIdAsc(User user);
    //Checking whether a menu item already exists in the user's cart
    Optional<Cart> findByUserAndMenuItem(User user,
                                         MenuItem menuItem);
    //Deleting cart items
    //Clearing entire cart
    void deleteByUser(User user);

    List<Cart> findByUser(User user);
}