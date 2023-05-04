package com.example.movierank.controller.core.userLogin;

import com.example.movierank.domain.user.login.UserLoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/login")
@SessionAttributes("userId")
public class UserLoginController {

    private final UserLoginServiceImpl userLoginService;

    @GetMapping("")
    public String loginForm(){

        return "thymeleaf/userLogin";
    }

    @PostMapping("")
    public String login(@RequestParam("userId") String userId, @RequestParam("password")String password, Model model){

        userLoginService.login(userId,password);
        model.addAttribute("userId",userId);
        return "redirect:/";
    }

}
