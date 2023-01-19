package com.esliceu.buckets.forms;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class RegisterForm {
    @Length(min = 5,max = 15)
    String nickname;
    @Email
    String email;
    @Length(min = 5, max = 15)
    String password;
    @Length(min = 3, max = 15)
    String name;
    @Length(min = 5, max = 30)
    String surnames;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String username) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }
}