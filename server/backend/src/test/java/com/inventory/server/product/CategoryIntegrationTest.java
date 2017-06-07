package com.inventory.server.product;

import com.inventory.server.product.model.Product;
import org.json.JSONArray;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryIntegrationTest {

    @Autowired private EmbeddedDatabase db;
    @Autowired private ProductRepository productRepository;
    @Autowired private TestRestTemplate restTemplate;

    @After
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void shouldReturnListOfTheCategoriesFromProducts() throws Exception {
        productRepository.save(new Product("foo", BigDecimal.ONE, "my category", UUID.randomUUID().toString()));
        productRepository.save(new Product("foo", BigDecimal.ONE, "my category 2", UUID.randomUUID().toString()));

        final JSONArray response = new JSONArray(restTemplate.getForObject("/api/categories", String.class));

        JSONAssert.assertEquals("[{\"name\":\"my category\"}, {\"name\":\"my category 2\"}]", response, true);
    }

    @Test
    public void shouldReturnEmptyListIfNoProductsAreAvailable() throws Exception {
        final JSONArray response = new JSONArray(restTemplate.getForObject("/api/categories", String.class));

        JSONAssert.assertEquals("[]", response, true);
    }

    @Test
    public void shouldReturnOnlyOneCategoryIfThereAreTwoProductsInSameCategory() throws Exception {
        productRepository.save(new Product("foo", BigDecimal.ONE, "my category", UUID.randomUUID().toString()));
        productRepository.save(new Product("foo", BigDecimal.ONE, "my category", UUID.randomUUID().toString()));

        final JSONArray response = new JSONArray(restTemplate.getForObject("/api/categories", String.class));

        JSONAssert.assertEquals("[{\"name\":\"my category\"}]", response, true);
    }
}
