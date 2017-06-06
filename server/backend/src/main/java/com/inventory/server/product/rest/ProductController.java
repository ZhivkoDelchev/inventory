package com.inventory.server.product.rest;

import com.inventory.server.product.ProductService;
import com.inventory.server.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< HEAD:server/backend/src/main/java/com/inventory/server/product/rest/ProductController.java
@RequestMapping("/api")
=======
>>>>>>> refs/heads/categoryController:server/backend/src/main/java/com/inventory/server/product/rest/ProductController.java
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryController categoryController;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @RequestMapping(path = "product/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Integer id) throws Exception {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

}
