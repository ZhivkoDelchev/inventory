package com.inventory.server.product.rest;

import com.inventory.server.product.model.Product;
import com.inventory.server.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")

public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Integer id) throws Exception {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

}