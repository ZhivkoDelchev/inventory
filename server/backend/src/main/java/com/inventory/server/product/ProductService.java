
package com.inventory.server.product;


import com.inventory.server.product.dto.Category;
import com.inventory.server.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) throws Exception {
        if (!this.productRepository.exists(id)) {
            throw new Exception("Product with this ID not exist.");
        }

        return this.productRepository.findOne(id);
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public List<Category> listCategories() {
        return productRepository.findAll().stream()
                .map(product -> new Category(product.getCategory()))
                .collect(Collectors.toList());
    }
}