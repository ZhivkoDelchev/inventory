package com.inventory.server.product.model;

import java.math.BigDecimal;

public class Product {

    private String name;

    private BigDecimal price;

    private String category;

    private String description;

    public Product(String name, BigDecimal price, String category, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }
}
