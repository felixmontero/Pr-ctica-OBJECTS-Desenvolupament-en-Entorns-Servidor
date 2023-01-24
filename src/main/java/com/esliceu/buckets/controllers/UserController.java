package com.esliceu.buckets.controllers;

import com.esliceu.buckets.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String homepage(Model model){

        return "homepage";
    }
    @GetMapping("/login")
    public String login(){

        return "login";
    }
    @PostMapping("/login")
    public String login(HttpServletRequest req){
        userService.login(req.getParameter("email"), req.getParameter("password"));
        return "login";
    }

    @GetMapping("/signup")
    public String register(){

        return "signup";
    }
    @PostMapping("/signup")
    public String register(@Valid String nickname, String email, String password, String name, String surnames){
        userService.register(nickname, email, password, name, surnames);
        return "signup";
    }
}
