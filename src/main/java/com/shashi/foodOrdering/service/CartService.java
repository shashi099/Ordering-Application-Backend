package com.shashi.foodOrdering.service;

import com.shashi.foodOrdering.dto.AddToCartRequest;
import com.shashi.foodOrdering.dto.AddToCartResponse;
import com.shashi.foodOrdering.entity.Cart;
import com.shashi.foodOrdering.entity.MenuItem;
import com.shashi.foodOrdering.entity.User;
import com.shashi.foodOrdering.exception.MenuItemNotFoundException;
import com.shashi.foodOrdering.exception.UserNotFoundException;
import com.shashi.foodOrdering.repository.CartRepository;
import com.shashi.foodOrdering.repository.MenuItemRepository;
import com.shashi.foodOrdering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    public AddToCartResponse addToCart(AddToCartRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found"));

        MenuItem menuItem = menuItemRepository
                .findById(request.getMenuItemId())
                .orElseThrow(() ->
                        new MenuItemNotFoundException("Menu Item Not Found"));

        Optional<Cart> existingCart =
                cartRepository.findByUserAndMenuItem(
                        user,
                        menuItem
                );

        if(existingCart.isPresent()) {

            Cart cart = existingCart.get();

            cart.setQuantity(
                    cart.getQuantity() + 1
            );

            Cart savedCart = cartRepository.save(cart);

            return mapToResponse(savedCart);
        }

        Cart cart = new Cart();

        cart.setUser(user);

        cart.setMenuItem(menuItem);

        cart.setQuantity(1);

        Cart savedCart = cartRepository.save(cart);

        return mapToResponse(savedCart);
    }

    public AddToCartResponse mapToResponse(Cart cart){

        return AddToCartResponse.builder()
                .cartId(cart.getCartId())
                .user(cart.getUser())
                .menuItem(cart.getMenuItem())
                .quantity(cart.getQuantity())
                .build();
    }

}
