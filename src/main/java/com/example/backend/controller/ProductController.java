package com.example.backend.controller;

import com.example.backend.model.Product;
import com.example.backend.service.ProductServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServicesImpl proServicesImpl;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProduct() {

        List<Product> body = proServicesImpl.listProducts();

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiController> createProduct(@RequestBody Product product) {

        try {

            if (Objects.nonNull(proServicesImpl.readProduct(product.getProductName()))) {

            }

            proServicesImpl.createProduct(product);
            return new ResponseEntity<>(new ApiController(true, "created the Product"), HttpStatus.CREATED);

        }catch(Exception e){

            e.printStackTrace();
            return new ResponseEntity<ApiController>(new ApiController(false,e.getMessage()), HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiController> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {

//        try{
//
//            if (Objects.nonNull(proServicesImpl.readProduct(id))) {
//
//            }
//            return new ResponseEntity<>(new ApiController(false, "products does not exist"), HttpStatus.NOT_FOUND);
//
//        }catch (Exception e) {
//
//            proServicesImpl.updateProduct(id, product);
//            return new ResponseEntity<ApiController>(new ApiController(true, "updated the product"), HttpStatus.OK);
//
//        }
        proServicesImpl.updateProduct(id, product);
            return new ResponseEntity<ApiController>(new ApiController(true, "updated the product"), HttpStatus.OK);



    }
}
