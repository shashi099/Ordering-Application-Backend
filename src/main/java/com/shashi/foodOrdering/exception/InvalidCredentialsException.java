package com.shashi.foodOrdering.exception;

public class InvalidCredentialsException
        extends RuntimeException{

    public InvalidCredentialsException(String message){
        super(message);
    }
}
