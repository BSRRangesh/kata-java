package com.online.bookstore.user.service.impl;

import com.online.bookstore.user.exception.UserAlreadyExistsException;
import com.online.bookstore.user.dto.UserRegistrationDTO;
import com.online.bookstore.user.entity.User;
import com.online.bookstore.user.repository.UserRepository;
import com.online.bookstore.user.service.CreateUserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserServiceImpl implements CreateUserService {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(UserRegistrationDTO userRegistrationDTO) {

        userRepository.findByUserName(userRegistrationDTO.getUserName())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("UserName already exists. Please try with different userId");
                });

        userRepository.findByEmail(userRegistrationDTO.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException("Email already exists. Please try with different email");
                });

        User user = new User();
        user.setUserName(userRegistrationDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setEmail(userRegistrationDTO.getEmail());

        userRepository.save(user);
        logger.info("User : {} created successfully", user.getUserName());
    }
}
