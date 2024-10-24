package com.online.bookstore.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserRegistrationDTO extends UserDTO {

    @NotEmpty(message = "Email is a mandatory field")
    @Email(message = "Invalid emailId")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}