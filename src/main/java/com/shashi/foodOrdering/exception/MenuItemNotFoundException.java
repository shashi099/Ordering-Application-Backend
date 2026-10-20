package com.shashi.foodOrdering.exception;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(String itemNotFound) {
        super(itemNotFound);
    }
}
