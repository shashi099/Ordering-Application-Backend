package com.shashi.foodOrdering.service;

import com.shashi.foodOrdering.dto.OrderRequest;
import com.shashi.foodOrdering.entity.Cart;
import com.shashi.foodOrdering.entity.Order;
import com.shashi.foodOrdering.entity.Restaurant;
import com.shashi.foodOrdering.entity.User;
import com.shashi.foodOrdering.enums.OrderStatus;
import com.shashi.foodOrdering.exception.RestaurantNotFoundException;
import com.shashi.foodOrdering.exception.UserNotFoundException;
import com.shashi.foodOrdering.repository.CartRepository;
import com.shashi.foodOrdering.repository.OrderRepository;
import com.shashi.foodOrdering.repository.RestaurantRepository;
import com.shashi.foodOrdering.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public Order placeOrder(OrderRequest request){

        Long userId = request.getUserId();
        Long restaurantId = request.getRestaurantId();

        System.out.println(userId + " => " + restaurantId);

        User user = userRepository.findById(userId)
                .orElseThrow(()->  new UserNotFoundException("User 404"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new RestaurantNotFoundException("Restaurant 404"));

        List<Cart> cartList = cartRepository.findByUserOrderByCartIdAsc(user);


        if(cartList.isEmpty()){ // Iska exception method change karna hai
            throw new RuntimeException("Cart Is Empty");
        }

        Double totalAmount = 0.0;

        for(Cart cart : cartList){
            double subTotal =
                    cart.getMenuItem().getPrice()
                            * cart.getQuantity();

            totalAmount += subTotal;
        }

        // Now save the Order

        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        cartRepository.deleteByUser(user);// user ka cart clear

        return savedOrder;
    }

}
