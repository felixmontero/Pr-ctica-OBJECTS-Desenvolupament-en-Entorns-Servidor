package com.esliceu.buckets.services;

import com.esliceu.buckets.daos.UserDAO;
import com.esliceu.buckets.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void register(String nickname, String email, String password,String name, String surnames) {
        userDAO.register(nickname, email, password, name, surnames);
    }

    public boolean login(String email, String password) {
       List<User> users= userDAO.getUser(email, password);
       if(users.isEmpty()){
           System.out.println("No existeix l'usuari");
           return false;
         }else {
           System.out.println("Usuari trobat");
           return true;
         }
    }

    public String encrypt(String password){
        //Encrypt password with SHA-256
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }

    public List<User> checkUser(String nickname){
        return userDAO.checkUser(nickname);
    }
}
