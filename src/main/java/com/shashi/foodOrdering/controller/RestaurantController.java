package com.shashi.foodOrdering.controller;

import com.shashi.foodOrdering.dto.ApiResponse;
import com.shashi.foodOrdering.dto.RestaurantRequest;
import com.shashi.foodOrdering.dto.RestaurantResponse;
import com.shashi.foodOrdering.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin
public class RestaurantController {

    private final RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @PostMapping()
    public ApiResponse<RestaurantResponse> registerRestaurant(@Valid @RequestBody RestaurantRequest request){

        RestaurantResponse response = restaurantService.createRestaurant(request);

        System.out.println(request);

        return ApiResponse.<RestaurantResponse>builder()
                .success(true)
                .message("Restaurant Added Successfully")
                .data(response).build();
    }

    // Show All restaurants
    @GetMapping()
    public ApiResponse<List<RestaurantResponse>> showRestaurants(){

        List<RestaurantResponse> responses = restaurantService.getAllRestaurants();

        return ApiResponse.<List<RestaurantResponse>>builder()
                .success(true)
                .message("Available Restaurants")
                .data(responses)
                .build();
    }

    // Get Particular Restaurants
    @GetMapping("/{id}")
    public ApiResponse<RestaurantResponse> getRestaurantById(@PathVariable Long id){

        RestaurantResponse response = restaurantService.getRestaurantById(id);

        return ApiResponse.<RestaurantResponse>builder()
                .success(true)
                .message("Found the Restaurants")
                .data(response)
                .build();

    }

    // Update the Restaurant Details
    @PutMapping("/{id}")
    public ApiResponse<RestaurantResponse> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantRequest request){

        RestaurantResponse response = restaurantService.updateRestaurant(id, request);

        return ApiResponse.<RestaurantResponse>builder()
                .success(true)
                .message("Restaurant Details Updated Successfully")
                .data(response)
                .build();
    }

    // Delete Particular Restaurants
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRestaurant(@PathVariable Long id){

        restaurantService.deleteRestaurant(id);
        return ApiResponse.<Void>builder()
                .success(true)
                .message("Deleted Successfully")
                .data(null)
                .build();
    }



}
