package com.example.movierank.domain.exception;

public class UserDuplicateSellerException extends RuntimeException{
    public UserDuplicateSellerException(String errorMessage){
        super(errorMessage);
    }

}
