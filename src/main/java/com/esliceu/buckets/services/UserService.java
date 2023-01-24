package com.esliceu.buckets.services;

import com.esliceu.buckets.daos.LoginDAO;
import com.esliceu.buckets.daos.RegisterDAO;
import com.esliceu.buckets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    RegisterDAO registerDAO;
    @Autowired
    LoginDAO loginDAO;

    public void register(String nickname, String email, String password,String name, String surnames) {
        registerDAO.register(nickname, email, password, name, surnames);
    }

    public void login(String email, String password) {
       List<User> users=loginDAO.getUser(email, password);
       if(users.isEmpty()){
           System.out.println("No existeix l'usuari");
         }else {
           System.out.println("Usuari trobat");
         }
    }
}
