package com.esliceu.buckets.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    @GetMapping("/")
    public String homepage(Model model){

        return "homepage";
    }
    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/register")
    public String register(){

        return "register";
    }
    @PostMapping("/register")
    public String register(HttpServletRequest req) {

        req.getParameter("nickname");
        req.getParameter("email");
        req.getParameter("password");
        req.getParameter("name");
        req.getParameter("surnames");
        return "register";
    }
}
