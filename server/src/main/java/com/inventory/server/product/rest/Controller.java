package com.inventory.server.product.rest;

import com.inventory.server.product.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class Controller {

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public Product getAll() {
        Product product = new Product("foo", BigDecimal.ONE);
        return product;
    }


}
