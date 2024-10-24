package com.online.bookstore.cart.controller;

import com.online.bookstore.cart.dto.CartRequestDTO;
import com.online.bookstore.cart.service.CartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Validated
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartRequestDTO>> getCart(@PathVariable(name= "userId") @NotNull String userId) {
        return new ResponseEntity<>(cartService.getCart(userId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Void> addToCart(@RequestBody @Valid CartRequestDTO cartRequestDTO) {
        cartService.addToCart(cartRequestDTO.getUserId(), cartRequestDTO.getBookId(), cartRequestDTO.getQuantity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/")
    public ResponseEntity<Void> updateCart(@RequestBody @Valid CartRequestDTO cartRequestDTO) {
        cartService.updateCart(cartRequestDTO.getUserId(), cartRequestDTO.getBookId(), cartRequestDTO.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> removeFromCart(@RequestParam(name= "userId") @NotNull String userId, @RequestParam(name= "bookId")
        @NotNull Integer bookId) {

        cartService.removeFromCart(userId, bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeAllFromCart(@PathVariable(name= "userId") @NotNull String userId) {
        cartService.removeAllFromCart(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
