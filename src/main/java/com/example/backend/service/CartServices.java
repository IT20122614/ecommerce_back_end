package com.example.backend.service;

import com.example.backend.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartServices {

    Cart readProduct(String productName);
    Optional<Cart> readProduct(Integer productId);
    void createProduct(Cart cart);
    List<Cart> listCarts(String username);
    void deleteCart(Integer id);
}
