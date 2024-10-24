package com.online.bookstore.user.service.impl;

import com.online.bookstore.user.dto.UserRegistrationDTO;
import com.online.bookstore.user.entity.User;
import com.online.bookstore.user.exception.UserAlreadyExistsException;
import com.online.bookstore.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserServiceMockTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserServiceImpl createUserService;

    private UserRegistrationDTO userRegistrationDTO;

    @BeforeEach
    public void setUp() {
        userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUserName("testUser");
        userRegistrationDTO.setPassword("testPassword");
        userRegistrationDTO.setEmail("test@example.com");
    }

    @Test
    @Transactional
    public void testCreateUser_Success() {
        when(userRepository.findByUserName(userRegistrationDTO.getUserName())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(userRegistrationDTO.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userRegistrationDTO.getPassword())).thenReturn("encodedPassword");

        createUserService.createUser(userRegistrationDTO);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @Transactional
    public void testCreateUser_UserNameAlreadyExists() {
        when(userRepository.findByUserName(userRegistrationDTO.getUserName())).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistsException.class, () -> createUserService.createUser(userRegistrationDTO));
    }

    @Test
    @Transactional
    public void testCreateUser_EmailAlreadyExists() {
        when(userRepository.findByEmail(userRegistrationDTO.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistsException.class, () -> createUserService.createUser(userRegistrationDTO));
    }
}
