package com.example.movierank.controller.core.userLogin;

import com.example.movierank.domain.user.login.UserLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/login")
public class UserLoginController {

    private final UserLoginServiceImpl userLoginService;

    @GetMapping("")
    public String loginForm(){

        return "thymeleaf/userLogin";
    }

    @PostMapping("")
    public String login(@RequestParam("userId") String userId,@RequestParam("password")String password){
        System.out.println(userId+"비버:"+password);

        userLoginService.login(userId,password);
        return "redirect:/";
    }

}
