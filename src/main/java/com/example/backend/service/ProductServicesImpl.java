package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServicesImpl implements ProductServices{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;


    @Override
    public List<Product> listProducts() {

        return productRepository.findAll();
    }

    @Override
    public void createProduct(Product product) {

        productRepository.save(product);
    }

    @Override
    public Product readProduct(String productName) {

        return productRepository.findByProductName(productName);
    }

    @Override
    public Optional<Product> readProduct(Integer productId) {

        return productRepository.findById(productId);
    }

    @Override
    public void updateProduct(Integer productId, Product newProduct) {

        Product product = productRepository.findById(productId).get();
        product.setProductName(newProduct.getProductName());
        product.setPrice(newProduct.getPrice());
        product.setDescription(newProduct.getDescription());
        product.setImageURL(newProduct.getImageURL());
        productRepository.save(product);
    }
}
