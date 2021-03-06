package com.inventory.server.persistence.repository;

import com.inventory.server.persistence.dto.Category;
import com.inventory.server.persistence.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();

    @Query(value = "SELECT DISTINCT category AS name FROM Product")
    List<Category> findCategories();

}
