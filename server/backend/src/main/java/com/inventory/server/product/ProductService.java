
package com.inventory.server.product;


import com.inventory.server.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public ResponseEntity<Product> createProduct(Product product) {

        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}