package com.example.backend.controller;

import com.example.backend.model.Cart;
import com.example.backend.service.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServices cartServices;

    @PostMapping("/add")
    public ResponseEntity<ApiController> createProduct(@RequestBody Cart cart) {

        try{

            if (Objects.nonNull(cartServices.readProduct(cart.getUsername()))) {

            }
            cartServices.createProduct(cart);
            return new ResponseEntity<>(new ApiController(true, "created the cart"), HttpStatus.CREATED);

        }catch(Exception e) {

            return new ResponseEntity<ApiController>(new ApiController(false, e.getMessage()), HttpStatus.CONFLICT);

        }



    }
    @GetMapping("/display/{id}")
    public ResponseEntity<List<Cart>> getCarts(@PathVariable("id") String id) {

        List<Cart> body = cartServices.listCarts(id);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiController> deleteCart(@PathVariable("id") Integer id) {

        cartServices.deleteCart(id);
        return new ResponseEntity<>(new ApiController(true, "deleted the cart"), HttpStatus.CREATED);

    }

}
