package com.inventory.server.product.rest;

import com.inventory.server.persistence.model.Picture;
import com.inventory.server.persistence.model.Product;
import com.inventory.server.product.service.PictureService;
import com.inventory.server.product.service.ProductService;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private PictureService pictureService;

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

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Integer id) throws Exception {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @RequestMapping(value = "/products/{id}/images", method = RequestMethod.POST)
    public @ResponseBody Picture upload(HttpServletRequest request, @PathVariable Integer id) {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                throw new RuntimeException("Not a multipart request.");
            }

            return pictureService.createPicture(readPictureData(request), id);

        } catch (FileUploadException e) {
            throw new RuntimeException("File upload error", e);
        } catch (IOException e) {
            throw new RuntimeException("Internal server IO error", e);
        }
    }

    private byte[] readPictureData(HttpServletRequest request) throws FileUploadException, IOException {
        ServletFileUpload upload = new ServletFileUpload();

        FileItemIterator iterator = upload.getItemIterator(request);
        if (iterator.hasNext()) {
            FileItemStream item = iterator.next();
            InputStream stream = item.openStream();
            if (!item.isFormField()) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                IOUtils.copy(stream, output);
                stream.close();

                return output.toByteArray();
            }
        }
        throw new RuntimeException("Missing file data");
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
