package com.shashi.foodOrdering.service;

import com.shashi.foodOrdering.dto.OrderDetailsResponse;
import com.shashi.foodOrdering.dto.OrderItemResponse;
import com.shashi.foodOrdering.dto.OrderRequest;
import com.shashi.foodOrdering.dto.OrderResponse;
import com.shashi.foodOrdering.entity.*;
import com.shashi.foodOrdering.enums.OrderStatus;
import com.shashi.foodOrdering.exception.CartQuantityException;
import com.shashi.foodOrdering.exception.RestaurantNotFoundException;
import com.shashi.foodOrdering.exception.UserNotFoundException;
import com.shashi.foodOrdering.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private OrderItemRepo orderItemRepo;  // SARE ORDER HUYE ITEM KO ORDER ITEM ME SAVE KARNE KE LIYE

    @Transactional
    public OrderResponse placeOrder(OrderRequest request){

        Long userId = request.getUserId();

        System.out.println(userId + " => " + userId);

        User user = userRepository.findById(userId)
                .orElseThrow(()->  new UserNotFoundException("User 404"));

        List<Cart> cartList = cartRepository.findByUserOrderByCartIdAsc(user);

        Long restaurantId = cartList.get(0).getMenuItem().getRestaurant().getRestaurantId();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
        .orElseThrow(()-> new RestaurantNotFoundException("Restaurant 404"));

        if(cartList.isEmpty()){ // Iska exception method change karna hai
            throw new CartQuantityException("User Has No Orders");
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

        // HUME ORDER ITEM KE UNDER SARE ORDER KO SAVE KAR LENA HAI
        // JIS SE HUM BAAD ME myorder ME SARE ITEM JO ORDER HUAA HAI SHOW KAR PAYENGE

        List<OrderItem> orderItemList = new ArrayList<>();

        for(Cart cart: cartList){

            MenuItem menuItem = cart.getMenuItem();

            double subTotal =  menuItem.getPrice() * cart.getQuantity();

            OrderItem orderItem =  new OrderItem();

            orderItem.setOrder(savedOrder);

            orderItem.setMenuItem(menuItem);

            orderItem.setQuantity(cart.getQuantity());

            orderItem.setPrice(menuItem.getPrice());

            orderItem.setSubTotal(subTotal);

            orderItemList.add(orderItem);
        }
        orderItemRepo.saveAll(orderItemList);

        // USER KA CART CLEAR MEANS ALL ITEM DELETE FROM CART
        cartRepository.deleteByUser(user);

        return mapToOrderResponse(savedOrder);

    }

    // Get All user Orders
    public List<OrderResponse> getUserOrders(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User Not Found"));

        List<Order> orders =orderRepository.findByUserOrderByCreatedAtDesc(user);

        return orders
                .stream()
                .map(this::mapToOrderResponse)
                .toList();
    }

    private OrderResponse mapToOrderResponse(Order order){

        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .restaurantName(order.getRestaurant().getRestaurantName())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }

    public OrderDetailsResponse getOrderDetails(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Need to Implement THis Exception"));


        List<OrderItemResponse> orderItemResponses = new ArrayList<>();

        for(OrderItem item : order.getOrderItemList()){

            OrderItemResponse response =
                    OrderItemResponse.builder()
                            .foodName(item.getMenuItem().getFoodName())
                            .quantity(item.getQuantity())
                            .price(item.getPrice())
                            .subTotal(item.getSubTotal())
                            .build();

            orderItemResponses.add(response);

        }



        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();

        orderDetailsResponse.setOrderId(order.getOrderId());
        orderDetailsResponse.setRestaurantName(order.getRestaurant().getRestaurantName());
        orderDetailsResponse.setTotalAmount(order.getTotalAmount());
        orderDetailsResponse.setCreatedAt(order.getCreatedAt());
        orderDetailsResponse.setStatus(order.getStatus());

        orderDetailsResponse.setOrderItemList(orderItemResponses);

        return orderDetailsResponse;

    }
}
