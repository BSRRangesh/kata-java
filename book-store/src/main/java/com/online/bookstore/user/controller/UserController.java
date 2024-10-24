package com.online.bookstore.user.controller;

import com.online.bookstore.user.dto.UserDTO;
import com.online.bookstore.user.dto.UserRegistrationDTO;
import com.online.bookstore.user.service.CreateUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CreateUserService createUserService;

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        createUserService.createUser(userRegistrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
