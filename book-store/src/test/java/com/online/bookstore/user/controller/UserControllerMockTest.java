package com.online.bookstore.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.bookstore.user.exception.UserAlreadyExistsException;
import com.online.bookstore.exception.handler.GlobalExceptionHandler;
import com.online.bookstore.user.dto.UserRegistrationDTO;
import com.online.bookstore.user.service.CreateUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.*;

class UserControllerMockTest {

    private MockMvc mockMvc;

    @Mock
    private CreateUserService createUserService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    void registerUserSuccess() throws Exception {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUserName("newUser");
        userRegistrationDTO.setEmail("newemail@example.com");
        userRegistrationDTO.setPassword("password");

        String userRegistrationJson = createJsonPayload(userRegistrationDTO);

        doNothing().when(createUserService).createUser(any(UserRegistrationDTO.class));

        mockMvc.perform(post("/users/register").contentType(MediaType.APPLICATION_JSON)
                        .content(userRegistrationJson))
                .andExpect(status().isCreated());
    }

    @Test
    void registerUserFailureUserNameExists() throws Exception {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUserName("existingUser");
        userRegistrationDTO.setEmail("existingemail@example.com");
        userRegistrationDTO.setPassword("password");

        String userRegistrationJson = createJsonPayload(userRegistrationDTO);

        doThrow(new UserAlreadyExistsException("UserName already exists. Please try with different userId"))
                .when(createUserService).createUser(any(UserRegistrationDTO.class));

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRegistrationJson))

                .andExpect(status().isBadRequest())
                .andExpect(content().string("UserName already exists. Please try with different userId"));
    }

    @Test
    void registerUserFailureEmailExists() throws Exception {
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setUserName("newUser2");
        userRegistrationDTO.setEmail("existingemail@example.com");
        userRegistrationDTO.setPassword("password");

        String userRegistrationJson = createJsonPayload(userRegistrationDTO);

        doThrow(new UserAlreadyExistsException("Email already exists. Please try with different email"))
                .when(createUserService).createUser(any(UserRegistrationDTO.class));

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRegistrationJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists. Please try with different email"));
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"user\",\"password\":\"password\"}"))
                .andExpect(status().isOk());
    }

    private static String createJsonPayload(UserRegistrationDTO userRegistrationDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(userRegistrationDTO);
    }
}
