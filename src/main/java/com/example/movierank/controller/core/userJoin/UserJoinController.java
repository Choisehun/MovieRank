package com.example.movierank.controller.core.userJoin;

import com.example.movierank.domain.user.join.service.UserDto;
import com.example.movierank.domain.user.join.service.UserJoinServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/join")
public class UserJoinController {

    private final UserJoinServiceImpl userJoinService;

    @GetMapping("")
    public String joinForm(){
        return "thymeleaf/userJoin";
    }


    @PostMapping("")
    public String join(UserDto userDto){
        userJoinService.add(userDto);
        return "thymeleaf/userLogin";
    }

}
