package com.esliceu.buckets.daos;

import com.esliceu.buckets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterDAOImpl implements RegisterDAO {
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
    public void register(String nickname, String email, String password, String name, String surnames) {
        jdbcTemplate.update("INSERT INTO users (nickname, email, password, name, surnames) VALUES (?, ?, ?, ?, ?, ?)",
                userRowMapper);
    }

}

