package com.example.movierank.domain.board;

import lombok.Getter;

@Getter
public class BoardDto {
    private final String content;

    public BoardDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
