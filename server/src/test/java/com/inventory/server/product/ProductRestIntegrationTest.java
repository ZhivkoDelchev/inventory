package com.inventory.server.product;

import com.inventory.server.product.model.Product;
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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRestIntegrationTest {

    @Autowired private EmbeddedDatabase db;
    @Autowired private ProductRepository productRepository;
    @Autowired private TestRestTemplate restTemplate;
    @Autowired private ProductService sut;

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
        db.shutdown();
    }

    @Test
    public void shouldReturnAllPersistedProducts() throws Exception {
        JSONArray expected = getJsonArray(productFoo, productBar);

        JSONArray response = new JSONArray(restTemplate.getForObject("/products", String.class));

        JSONAssert.assertEquals(expected, response, false);
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

            return jsonObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
