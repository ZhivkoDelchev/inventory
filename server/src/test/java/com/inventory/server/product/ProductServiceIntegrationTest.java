package com.inventory.server.product;

import com.google.common.collect.Iterables;
import com.inventory.server.product.model.Product;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTest {

    @Autowired private EmbeddedDatabase db;
    @Autowired private ProductRepository productRepository;
    @Autowired private ProductService sut;

    @After
    public void tearDown() {
        productRepository.deleteAll();
        db.shutdown();
    }

    @Test
    public void shouldReturnAllPersistedProducts() throws Exception {
        Product foo = new Product("foo", BigDecimal.ONE, "my category", "lets describe smth");
        foo = productRepository.save(foo);
        Product bar = new Product("bar", BigDecimal.TEN, "my category", "lets describe smth again");
        bar = productRepository.save(bar);

        final List<Product> result = sut.getAllProducts();
        assertEquals(2, Iterables.size(result));
        assertTrue(result.contains(foo));
        assertTrue(result.contains(bar));
    }
}
