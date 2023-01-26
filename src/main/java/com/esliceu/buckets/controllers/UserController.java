package com.esliceu.buckets.controllers;

import com.esliceu.buckets.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public String login(@RequestParam String nickname, @RequestParam String password){
        userService.login(nickname, userService.encrypt(password));
        System.out.printf("nickname:"+nickname+" password:"+password);
        return "objects";
    }

    @GetMapping("/signup")
    public String register(){

        return "signup";
    }
    @PostMapping("/signup")
    public String register(@Valid @RequestParam String nickname, @RequestParam String email, @RequestParam String password, @RequestParam String name, @RequestParam String surnames){
        userService.register(nickname, email, userService.encrypt(password), name, surnames);
        return "signup";
    }

    @GetMapping("/settings")
    public String settings(){

        return "settings";
    }


    // create a method to download a file
    /*@GetMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) {
        byte[] content = file.getContent();
        String name = obj.getName();
        HttpHeaders headers = new HttpHeaders();
        headers.getContentType(MediaType.Value.of("application/octet-stream"));
        headers.setContentLength(content.length);
        headers.set("Content-Disposition", "attachment; filename=" + name);
        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }*/
}
