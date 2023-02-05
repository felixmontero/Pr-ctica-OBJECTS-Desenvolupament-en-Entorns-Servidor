package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rn) -> {
        User user = new User();
        user.setNickname(rs.getString("nickname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setName(rs.getString("name"));
        user.setSurnames(rs.getString("surnames"));

        return user;
    };
    @Override
    public List<User> getUser(String nickname, String password){
        return jdbcTemplate.query("SELECT * FROM users WHERE nickname = (?) AND password = (?)",
                new Object[]{ nickname, password}, userRowMapper);

    }

    @Override
    public void register(String nickname, String email, String password, String name, String surnames) {
        jdbcTemplate.update("INSERT INTO users (nickname, email, password, name, surnames) VALUES (?, ?, ?, ?, ?)",
                nickname, email, password, name, surnames);
    }

    @Override
    public User checkUser(String nickname) {
       return jdbcTemplate.queryForObject("SELECT * FROM users WHERE nickname = (?) ",
               new Object[]{ nickname}, userRowMapper);
    }

    @Override
    public void updateUser(String nickname, String email, String encrypt, String name, String surnames) {
        jdbcTemplate.update("UPDATE users SET email = (?), password = (?), name = (?), surnames = (?) WHERE nickname = (?)",
                email, encrypt, name, surnames, nickname);
    }

    @Override
    public void deleteUser(String nickname) {

        //delete user on cascade
        jdbcTemplate.update("DELETE FROM users WHERE nickname = (?)", nickname);
    }
}
