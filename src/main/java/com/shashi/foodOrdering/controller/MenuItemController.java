package com.shashi.foodOrdering.controller;

import com.shashi.foodOrdering.dto.ApiResponse;
import com.shashi.foodOrdering.dto.MenuItemRequest;
import com.shashi.foodOrdering.dto.MenuItemResponse;
import com.shashi.foodOrdering.service.MenuItemService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }


    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<ApiResponse<MenuItemResponse>> addMenuItem(
            @PathVariable Long restaurantId,
            @Valid @RequestBody MenuItemRequest request){

        System.out.println(restaurantId + " " + request);

        MenuItemResponse response = menuItemService.createMenuItem(restaurantId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<MenuItemResponse>builder()
                .success(true)
                .message("Menu Item Added Successfully")
                .data(response)
                .build());
    }

    // Get All Menu Items
    @GetMapping("/{restaurantId}/menu")
    public ResponseEntity<ApiResponse<List<MenuItemResponse>>> getALlMenuItems(@PathVariable Long restaurantId){

        List<MenuItemResponse> items = menuItemService.getMenuByRestaurant(restaurantId);

        return ResponseEntity.ok(
                ApiResponse
                        .<List<MenuItemResponse>>builder()
                        .success(true)
                        .message(
                                "Menu fetched successfully")
                        .data(items)
                        .build()
        );
    }

}
