package com.online.bookstore.user.service.impl;

import com.online.bookstore.user.entity.User;
import com.online.bookstore.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasicUserLoadServiceMockTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BasicUserLoadService basicUserLoadService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserName("testUser");
        user.setPassword("testPassword");
    }

    @Test
    public void testLoadUserSuccess() {
        when(userRepository.findByUserName("user")).thenReturn(Optional.of(user));

        UserDetails userDetails = basicUserLoadService.loadUserByUsername("user");

        assertEquals("testUser", userDetails.getUsername());
    }

    @Test
    public void testLoadUserFailure() {
        when(userRepository.findByUserName("user1")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> basicUserLoadService.loadUserByUsername("user1"));
    }

}
