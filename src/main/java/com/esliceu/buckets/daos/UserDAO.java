package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserDAO {

    List<User> getUser(String email, String password);

    void register(String nickname, String email, String password, String name, String surnames);

    User checkUser(String nickname);

    void updateUser(String nickname, String email, String encrypt, String name, String surnames);

    void deleteUser(String nickname);
}
