package com.shashi.foodOrdering.service;

import com.shashi.foodOrdering.dto.*;
import com.shashi.foodOrdering.entity.Cart;
import com.shashi.foodOrdering.entity.MenuItem;
import com.shashi.foodOrdering.entity.User;
import com.shashi.foodOrdering.exception.CartNotFoundException;
import com.shashi.foodOrdering.exception.CartQuantityException;
import com.shashi.foodOrdering.exception.MenuItemNotFoundException;
import com.shashi.foodOrdering.exception.UserNotFoundException;
import com.shashi.foodOrdering.repository.CartRepository;
import com.shashi.foodOrdering.repository.MenuItemRepository;
import com.shashi.foodOrdering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    // All carts of a User
    public CartResponse getUserCartItems(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User Not Found While Searching for getUserCartItems"));

        List<Cart> carts = cartRepository.findByUser(user);

        // Basically we are returning the reponses in Better way
        // By the way we can return the carts itself
        // Here we are refining the particular field that we need

        List<CartItemResponse> itemResponses = new ArrayList<>();

        double totalAmount = 0.0;

        for (Cart cart: carts) {

            MenuItem menuItem = cart.getMenuItem();

            double subTotal = menuItem.getItem_price() * cart.getQuantity();

            totalAmount += subTotal;

            CartItemResponse eachCartItem = new CartItemResponse(
                    menuItem.getMenuId(),
                    menuItem.getItem_name(),
                    menuItem.getItem_price(),
                    cart.getQuantity(),
                    subTotal
            );

            itemResponses.add(eachCartItem);
        }

        return new CartResponse(itemResponses, totalAmount);
    }

    // Cart Quantity update ho raha hai ..............
    public Cart updateCartQuantity(Long cartId, UpdateQuantityRequest request) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()-> new CartNotFoundException("Cart Not Found!!"));

        System.out.println(cart);

        if(request.getQuantity() <= 0){
            throw new CartQuantityException("Quantity Should be more than 0 ");
        }

        cart.setQuantity(request.getQuantity());

        Cart savedCart = cartRepository.save(cart);

        return savedCart;
    }

    public void removeCartItem(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()-> new CartNotFoundException("Cart 404 during Deletion"));

        cartRepository.delete(cart);
    }
}
