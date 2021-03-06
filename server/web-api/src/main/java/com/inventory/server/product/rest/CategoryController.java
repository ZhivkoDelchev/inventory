package com.inventory.server.product.rest;

import com.inventory.server.product.service.ProductService;
import com.inventory.server.persistence.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<Category> listCategory() {
        return productService.listCategories();
    }
}
