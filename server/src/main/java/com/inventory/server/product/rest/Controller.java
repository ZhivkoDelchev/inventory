package com.inventory.server.product.rest;

import com.google.common.base.Preconditions;
import com.inventory.server.product.ProductRepository;
import com.inventory.server.product.ProductService;
import com.inventory.server.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class Controller {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @RequestMapping(path = "product/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Integer id) {
        if (!this.productRepository.exists(id)) {
            return null;
        }

        Product product = this.productRepository.findOne(id);

        return product;
    }

    @RequestMapping(value = "product/create", method = RequestMethod.POST)
    public ResponseEntity<Product> update(@RequestBody Product product) {

        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
