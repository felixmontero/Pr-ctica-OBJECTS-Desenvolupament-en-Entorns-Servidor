package com.esliceu.buckets.services;

import com.esliceu.buckets.daos.LoginDAO;
import com.esliceu.buckets.daos.RegisterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    RegisterDAO registerDAO;
    @Autowired
    LoginDAO loginDAO;

    public void register(String nickname, String email, String password,String name, String surnames) {
        registerDAO.register(nickname, email, password, name, surnames);
        System.out.println(("nickname in service " + nickname));
    }

    public void login(String email, String password) {
        loginDAO.login(email, password);
    }
}
