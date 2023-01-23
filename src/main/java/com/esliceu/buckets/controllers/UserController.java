package com.esliceu.buckets.controllers;

import com.esliceu.buckets.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public String register(HttpServletRequest req) {
        userService.register(req.getParameter("nickname"), req.getParameter("email"),
                req.getParameter("password"), req.getParameter("name"), req.getParameter("surnames"));
        return "signup";
    }
}
