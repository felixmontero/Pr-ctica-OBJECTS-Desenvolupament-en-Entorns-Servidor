package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.User;

import java.util.List;

public interface LoginDAO {

    List<User> getUser(String email, String password);
}
