package com.inventory.server.product.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category {
    @NotNull
    @Size(max = 60)
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
