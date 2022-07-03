package com.example.backend.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private @NotNull String productName;
    private @NotNull String username;

    private @NotNull Float price;
    private @NotNull String description;
    private @NotNull String imageURL;

    public Cart() {
    }

    public Cart(String username, String productName, Float price, String description, String imageURL) {
        this.username = username;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
    }

    public Cart(Integer id, String username, String productName, Float price, String description, String imageURL) {
        this.id = id;
        this.username = username;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
    }

    public Cart(String productName, Float price, String description, String imageURL) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imageURL = imageURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
