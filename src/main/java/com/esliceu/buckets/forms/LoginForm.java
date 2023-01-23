package com.esliceu.buckets.forms;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class LoginForm {
    @Length(min = 5, max = 15)
    private String nickname;
    @Length(min = 5, max = 15)
    private String password;

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
