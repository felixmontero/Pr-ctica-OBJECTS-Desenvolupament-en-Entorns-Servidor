package com.esliceu.buckets.services;

import com.esliceu.buckets.daos.LoginDAO;
import com.esliceu.buckets.daos.RegisterDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    RegisterDAO registerDAO;
    LoginDAO loginDAO;

    public void register(String nickname, String email, String password,String name, String surnames) {
        registerDAO.register(nickname, email, password, name, surnames);
    }

    public void login(String email, String password) {
        loginDAO.login(email, password);
    }
}
