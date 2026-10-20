package com.shashi.foodOrdering.repository;

import com.shashi.foodOrdering.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

//    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.restaurant_id = :restaurantId")
//    List<MenuItem> findMenuItemsByRestaurantId(@Param("restaurantId") Long restaurantId);
    List<MenuItem> findByRestaurantRestaurantId(Long restaurantId);

}
