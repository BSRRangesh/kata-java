package com.online.bookstore.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotEmpty(message = "Username is a mandatory field")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
    private String userName;

    @NotEmpty(message = "Password is a mandatory field")
    @Size(min = 8, message = "Password must be minimum of 8 characters")
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}