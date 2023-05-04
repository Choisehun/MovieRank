package com.example.movierank.domain.board.service;

import com.example.movierank.domain.board.BoardDto;
import com.example.movierank.domain.board.domain.Board;
import com.example.movierank.domain.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public void insert(String content) {
        Board board = new Board(
                content);

    boardRepository.save(board);

    }


}
