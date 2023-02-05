package com.esliceu.buckets.controllers;

import com.esliceu.buckets.models.User;
import com.esliceu.buckets.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String login(@RequestParam String nickname, @RequestParam String password, HttpServletRequest req){
        HttpSession session = req.getSession();
        boolean login = userService.login(nickname, userService.encrypt(password));
        if(login){
            session.setAttribute("nickname", nickname);
            return "redirect:/settings";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String register(){

        return "signup";
    }
    @PostMapping("/signup")
    public String register(@Valid @RequestParam String nickname,@Valid @RequestParam String email,@Valid @RequestParam String password,
                           @Valid @RequestParam String name,@Valid @RequestParam String surnames){
        userService.register(nickname, email, userService.encrypt(password), name, surnames);
        return "signup";
    }

    @GetMapping("/settings")
    public String settings(HttpServletRequest req, Model model){
        HttpSession session = req.getSession();

        User user = userService.checkUser((String) session.getAttribute("nickname"));
       req.setAttribute("user", user);
       model.addAttribute("user", user);
        return "settings";
    }

    @PostMapping("/settings")
    public String settings(@Valid @RequestParam String email,@Valid @RequestParam String password,
                           @Valid @RequestParam String name,@Valid @RequestParam String surnames, HttpServletRequest req){
        if(password.equals("")){
            password = userService.checkUser((String) req.getSession().getAttribute("nickname")).getPassword();
        }
        else{
            password = userService.encrypt(password);
        }

        userService.updateUser((String) req.getSession().getAttribute("nickname"), email, userService.encrypt(password), name, surnames);
        return "redirect:/objects";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:/";
    }
    @PostMapping("/delete")
    public String delete(HttpServletRequest req){
        HttpSession session = req.getSession();
        userService.deleteUser((String) session.getAttribute("nickname"));
        session.invalidate();
        return "redirect:/";
    }
}
