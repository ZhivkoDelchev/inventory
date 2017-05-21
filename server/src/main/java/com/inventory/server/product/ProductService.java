package com.inventory.server.product;


import com.inventory.server.product.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {
        List<Product> products = Arrays.asList(new Product("Foo", BigDecimal.TEN, "categoryValue", "descriptionValue"));
        return products;
    }

}
