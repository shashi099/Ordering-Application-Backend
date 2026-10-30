package com.shashi.foodOrdering.service;

import com.shashi.foodOrdering.dto.RestaurantRequest;
import com.shashi.foodOrdering.dto.RestaurantResponse;
import com.shashi.foodOrdering.entity.Restaurant;
import com.shashi.foodOrdering.exception.RestaurantNotFoundException;
import com.shashi.foodOrdering.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
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

        // Check whether the restaurant is already exist -> CODE PENDING

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setRestaurantAddress(request.getRestaurantAddress());
        restaurant.setRestaurantImage(
                (request.getRestaurantImage() == null || request.getRestaurantImage().trim().isEmpty())
                        ? "https://img.magnific.com/free-photo/restaurant-interior_1127-3394.jpg?semt=ais_hybrid&w=740&q=80"
                        : request.getRestaurantImage()
        );
        restaurant.setRestaurantRating(request.getRestaurantRating());
        System.out.println("Rating " + request.getRestaurantRating());


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

        restaurant.setRestaurantId(id);
        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setRestaurantAddress(request.getRestaurantAddress());
        restaurant.setRestaurantImage(restaurant.getRestaurantImage());

        Restaurant updated = restaurantRepository.save(restaurant);

        return mapToResponse(updated);
    }

    // Remove Restaurant
    @Transactional
    public void deleteRestaurant(Long id){

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("restaurant not found"));

        restaurantRepository.delete(restaurant);

    }

    // Method return the RestaurantResponse
    public RestaurantResponse mapToResponse(Restaurant restaurant){

        return RestaurantResponse.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .restaurantAddress(restaurant.getRestaurantAddress())
                .restaurantRating(restaurant.getRestaurantRating())
                .restaurantImage(restaurant.getRestaurantImage())
                .build();

    }
}
