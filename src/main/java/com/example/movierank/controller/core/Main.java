package com.example.movierank.controller.core;

import com.example.movierank.domain.board.domain.Board;
import com.example.movierank.domain.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Main {

        private final BoardRepository boardRepository;
    @GetMapping("/")
    public String move(Pageable pageable,Model model){

            int page = Math.max(pageable.getPageNumber(), 1);
            int limitPage = 10;

            Page<Board> list = boardRepository.findAll(PageRequest.of(page - 1, limitPage, Sort.Direction.DESC, "id"));
            model.addAttribute("list", list);

            int totalPage = list.getTotalPages();
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("page", page);




        return "thymeleaf/main";
    }
}
