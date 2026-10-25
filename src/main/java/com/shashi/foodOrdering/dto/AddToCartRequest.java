package com.shashi.foodOrdering.dto;



public class AddToCartRequest {
    private Long userId;

    private Long menuItemId;

    public AddToCartRequest() {
    }

    public AddToCartRequest(Long userId, Long menuItemId) {
        this.userId = userId;
        this.menuItemId = menuItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

}
