package com.example.backend.service;

import com.example.backend.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServices {

    List<Product> listProducts();
    void createProduct(Product product);
    Product readProduct(String productName);
    Optional<Product> readProduct(Integer productId);
    void updateProduct(Integer productId, Product newProduct);
}
