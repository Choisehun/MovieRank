package com.example.movierank.controller.core.board;

import com.example.movierank.domain.board.service.BoardServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/insert")
public class InsertBoard {

    private final BoardServiceImpl boardService;

    @PostMapping("")
    public String insert(@RequestParam("content") String content){
        System.out.println(content);
        boardService.insert(content);

        return "thymeleaf/main";

    }

}
