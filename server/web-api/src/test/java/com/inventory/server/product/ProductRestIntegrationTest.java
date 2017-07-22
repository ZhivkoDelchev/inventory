package com.inventory.server.product;

import com.inventory.server.persistence.model.Product;
import com.inventory.server.persistence.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRestIntegrationTest {

    @Autowired private EmbeddedDatabase db;
    @Autowired private ProductRepository productRepository;
    @Autowired private TestRestTemplate restTemplate;

    private Product productFoo;
    private Product productBar;

    @Before
    public void setUp() throws Exception {
        productFoo = productRepository.save(new Product("foo", BigDecimal.ONE, "my category", UUID.randomUUID().toString()));
        productBar = productRepository.save(new Product("bar", BigDecimal.TEN, "my category", UUID.randomUUID().toString()));
    }

    @After
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void shouldReturnAllPersistedProducts() throws Exception {
        final JSONArray expected = getJsonArray(productFoo, productBar);

        final JSONArray response = new JSONArray(restTemplate.getForObject("/api/products", String.class));

        JSONAssert.assertEquals(expected, response, true);
    }

    @Test
    public void shouldPersistNewProduct() throws Exception {
        final Product newProduct = new Product("baz", BigDecimal.ZERO, "my category", UUID.randomUUID().toString());

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> httpEntity = new HttpEntity<>(gerProductAsJsonObject(newProduct).toString(), headers);

        final ResponseEntity<String> result = restTemplate.exchange("/api/products", HttpMethod.POST, httpEntity, String.class);

        assertThat(result.getStatusCode(), is(equalTo(HttpStatus.OK)));
        final List<Product> persistedProducts = productRepository.findAll();
        JSONAssert.assertEquals( getJsonArray(productFoo, productBar, newProduct), getJsonArray(persistedProducts.toArray(new Product[0])), false);
    }

    private JSONArray getJsonArray(Product... products) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        Arrays.stream(products).map(this::gerProductAsJsonObject).forEach(jsonArray::put);
        return jsonArray;
    }

    private JSONObject gerProductAsJsonObject(final Product product) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", product.getId());
            jsonObject.put("category", product.getCategory());
            jsonObject.put("description", product.getDescription());
            jsonObject.put("name", product.getName());
            jsonObject.put("price", product.getPrice());
            jsonObject.put("picture", new JSONArray());

            return jsonObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
