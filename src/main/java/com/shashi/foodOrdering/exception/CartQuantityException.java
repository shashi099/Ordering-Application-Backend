package com.shashi.foodOrdering.exception;

public class CartQuantityException extends RuntimeException {
    public CartQuantityException(String message){
        super(message);
    }
}
