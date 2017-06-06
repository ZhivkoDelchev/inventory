
package com.inventory.server.product;


import com.inventory.server.product.dto.Category;
import com.inventory.server.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


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
        List<Product> products = productRepository.findAll();
        Set<String> categorySet = new LinkedHashSet<>();

        for (Product product : products) {
            categorySet.add(product.getCategory());
        }

        List<Category> categories = new ArrayList<>();
        categorySet.stream().sorted().forEach(s -> categories.add(new Category(s)));

        return categories;
    }
}