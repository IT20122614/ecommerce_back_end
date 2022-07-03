package com.example.backend.repository;


import com.example.backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("SELECT u FROM Cart u WHERE u.username = ?1")
    List<Cart> listCart(String username);


    Cart findByProductName(String productName);

}
