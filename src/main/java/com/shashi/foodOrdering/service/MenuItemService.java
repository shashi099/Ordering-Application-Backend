package com.shashi.foodOrdering.service;


import com.shashi.foodOrdering.dto.ApiResponse;
import com.shashi.foodOrdering.dto.MenuItemRequest;
import com.shashi.foodOrdering.dto.MenuItemResponse;
import com.shashi.foodOrdering.entity.MenuItem;
import com.shashi.foodOrdering.entity.Restaurant;
import com.shashi.foodOrdering.exception.RestaurantNotFoundException;
import com.shashi.foodOrdering.repository.MenuItemRepository;
import com.shashi.foodOrdering.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository){
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // save the Menu Item

    public MenuItemResponse createMenuItem(Long restaurantId,  MenuItemRequest request){

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new RestaurantNotFoundException(("Restaurant Not Found")));

        MenuItem item = new MenuItem();

        item.setItem_name(request.getItem_name());
        item.setItem_description(request.getItem_description());
        item.setItem_price(request.getItem_price());
        item.setItem_available(request.getItem_available());

        item.setRestaurant(restaurant);

        MenuItem savedItem = menuItemRepository.save(item);

        return mapToResponse(savedItem);
    }

    // Get All Menu Items
    public List<MenuItemResponse> getMenuByRestaurant(Long restaurantId){

        return menuItemRepository.findByRestaurantRestaurantId(restaurantId)
                .stream().map(this::mapToResponse)
                .toList();
    }

    // Method return MenuItemResponse
    private MenuItemResponse mapToResponse(MenuItem item){

        return MenuItemResponse.builder()
                .menuId(item.getMenuId())
                .item_name(item.getItem_name())
                .item_description(item.getItem_description())
                .item_price(item.getItem_price())
                .item_available(item.getItem_available())
                .restaurantId(item.getRestaurant()
                        .getRestaurantId())
                .restaurantName(item.getRestaurant()
                        .getRestaurant_name())
                .build();
    }


}