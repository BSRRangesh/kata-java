package com.online.bookstore.cart.controller;

import com.online.bookstore.cart.dto.CartRequestDTO;
import com.online.bookstore.cart.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartControllerMockTest {


    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    private CartRequestDTO cartRequestDTO;

    @BeforeEach
    public void setUp() {
        cartRequestDTO = new CartRequestDTO();
        cartRequestDTO.setUserId("user1");
        cartRequestDTO.setBookId(1);
        cartRequestDTO.setQuantity(2);
    }


    @Test
    public void testGetCartNoEntries() {
        when(cartService.getCart(anyString())).thenReturn(null);

        ResponseEntity<List<CartRequestDTO>> response = cartController.getCart("");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetCartWithEntries() {
        List<CartRequestDTO> cartItems = Collections.singletonList(cartRequestDTO);
        when(cartService.getCart("user2")).thenReturn(cartItems);

        ResponseEntity<List<CartRequestDTO>> response = cartController.getCart("user2");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItems, response.getBody());
    }

    @Test
    public void testAddToCart() {
        doNothing().when(cartService).addToCart("user1", 1, 2);

        ResponseEntity<Void> response = cartController.addToCart(cartRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(cartService, times(1)).addToCart("user1", 1, 2);
    }

    @Test
    public void testUpdateCart() {
        doNothing().when(cartService).updateCart("user1", 1, 2);

        ResponseEntity<Void> response = cartController.updateCart(cartRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).updateCart("user1", 1, 2);
    }

    @Test
    public void testRemoveFromCart() {
        doNothing().when(cartService).removeFromCart("user1", 1);

        ResponseEntity<Void> response = cartController.removeFromCart("user1", 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).removeFromCart("user1", 1);
    }

    @Test
    public void testRemoveAllFromCart() {
        doNothing().when(cartService).removeAllFromCart("user1");

        ResponseEntity<Void> response = cartController.removeAllFromCart("user1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService, times(1)).removeAllFromCart("user1");
    }
}
