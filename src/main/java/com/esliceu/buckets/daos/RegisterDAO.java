package com.esliceu.buckets.daos;

public interface RegisterDAO {

    void register(String nickname, String email, String password, String name, String surnames);
}
