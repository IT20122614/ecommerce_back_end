package com.example.backend.service;

import com.example.backend.model.Cart;
import com.example.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartServicesImpl implements CartServices{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart readProduct(String productName) {
        return cartRepository.findByProductName(productName);
    }

    @Override
    public Optional<Cart> readProduct(Integer productId) {
        return cartRepository.findById(productId);
    }

    @Override
    public void createProduct(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> listCarts(String username) {
        return cartRepository.listCart(username);
    }

    @Override
    public void deleteCart(Integer id) {
        cartRepository.deleteById(id);
    }
}
