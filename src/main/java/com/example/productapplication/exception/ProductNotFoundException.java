package com.example.productapplication.exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(){

    }
    public ProductNotFoundException(String message){
        super(message);
    }
}
