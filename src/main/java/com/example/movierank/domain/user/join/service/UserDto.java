package com.example.movierank.domain.user.join.service;

import lombok.Getter;

@Getter
public class UserDto {
    private final String userId;
    private final String password;
    private final String email;

    public UserDto(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }
}
