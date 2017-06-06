package com.inventory.server.product.rest;

import com.inventory.server.product.ProductService;
import com.inventory.server.product.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
<<<<<<< HEAD:server/backend/src/main/java/com/inventory/server/product/rest/CategoryController.java
@RequestMapping("/api")
=======
>>>>>>> refs/heads/categoryController:server/backend/src/main/java/com/inventory/server/product/rest/CategoryController.java
public class CategoryController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<Category> listCategory() {
        return productService.listCategories();
    }
}
