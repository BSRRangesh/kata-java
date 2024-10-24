package com.online.bookstore.user.service;

import com.online.bookstore.user.dto.UserDTO;
import com.online.bookstore.user.dto.UserRegistrationDTO;
import jakarta.transaction.Transactional;

public interface CreateUserService {

    @Transactional
    void createUser(UserRegistrationDTO userRegistrationDTO);
}
