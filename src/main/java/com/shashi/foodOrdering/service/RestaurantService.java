package com.shashi.foodOrdering.service;

import com.shashi.foodOrdering.dto.RestaurantRequest;
import com.shashi.foodOrdering.dto.RestaurantResponse;
import com.shashi.foodOrdering.entity.Restaurant;
import com.shashi.foodOrdering.exception.RestaurantNotFoundException;
import com.shashi.foodOrdering.repository.RestaurantRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    // Admin create the restaurant
    public RestaurantResponse createRestaurant(RestaurantRequest request){

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurant_name(request.getName());
        restaurant.setRestaurant_address(request.getRestaurant_address());
        restaurant.setRestaurant_image(
                (request.getRestaurant_image() == null || request.getRestaurant_image().trim().isEmpty())
                        ? "https://img.magnific.com/free-photo/restaurant-interior_1127-3394.jpg?semt=ais_hybrid&w=740&q=80"
                        : request.getRestaurant_image()
        );
        restaurant.setRestaurant_rating(request.getRestaurant_rating());
        System.out.println("Rating " + request.getRestaurant_rating());


        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return mapToResponse(savedRestaurant);


    }

    // Get All Restaurants
    public List<RestaurantResponse> getAllRestaurants(){

        return restaurantRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());

    }

    // Get Restaurant By Id
    public RestaurantResponse getRestaurantById(Long id){

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not Found"));

        return mapToResponse(restaurant);
    }

    // Update Restaurant
    public RestaurantResponse updateRestaurant(Long id,  RestaurantRequest request){

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("restaurant not found"));

        restaurant.setRestaurant_id(id);
        restaurant.setRestaurant_name(request.getName());
        restaurant.setRestaurant_address(request.getRestaurant_address());
        restaurant.setRestaurant_image(restaurant.getRestaurant_image());

        Restaurant updated = restaurantRepository.save(restaurant);

        return mapToResponse(updated);
    }

    // Remove Restaurant
    public void deleteRestaurant(Long id){

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("restaurant not found"));

        restaurantRepository.delete(restaurant);

    }

    // Method return the RestaurantResponse
    public RestaurantResponse mapToResponse(Restaurant restaurant){

        return RestaurantResponse.builder()
                .restaurant_id(restaurant.getRestaurant_id())
                .restaurant_name(restaurant.getRestaurant_name())
                .restaurant_address(restaurant.getRestaurant_address())
                .restaurant_rating(restaurant.getRestaurant_rating())
                .restaurant_image(restaurant.getRestaurant_image())
                .build();

    }
}
