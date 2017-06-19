package com.inventory.server.product.rest;

import com.inventory.server.product.ProductService;
import com.inventory.server.persistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController

@RequestMapping("/api")

public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @RequestMapping(path = "/products/page/{page}", method = RequestMethod.GET)
    public ProductPage getPaged(@PathVariable Integer page) {
        int dummyProductsCount = 100;
        List<Product> dummyProductList = createDummyProducts(dummyProductsCount);
        return new ProductPage(dummyProductsCount, dummyProductList);
    }

    @RequestMapping(path = "product/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Integer id) throws Exception {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    private List<Product> createDummyProducts(int dummyProductsCount) {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < dummyProductsCount; i++) {
            products.add(createDummyProduct(i));
        }
        return products;
    }

    private Product createDummyProduct(int counter) {
        return new Product("product" + counter, new BigDecimal(counter), "category" + (counter % 10), UUID.randomUUID().toString());
    }

    class ProductPage {

        private int total;
        private List<Product> data;

        ProductPage(int total, List<Product> data) {
            this.total = total;
            this.data = data;
        }

        public int getTotal() {
            return total;
        }

        public List<Product> getData() {
            return data;
        }
    }
}
