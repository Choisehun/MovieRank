package com.example.movierank.domain.exception;

public class NotPasswordException extends RuntimeException{
    public NotPasswordException(String errorMessage){
        super(errorMessage);
    }

}
