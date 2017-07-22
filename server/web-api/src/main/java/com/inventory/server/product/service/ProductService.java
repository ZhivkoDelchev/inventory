package com.inventory.server.product.service;

import com.inventory.server.persistence.dto.Category;
import com.inventory.server.persistence.model.Product;
import com.inventory.server.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public List<Category> listCategories() {

        return productRepository.findCategories();

    }
}