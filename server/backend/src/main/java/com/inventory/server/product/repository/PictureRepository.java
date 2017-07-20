package com.inventory.server.product.repository;

import com.inventory.server.product.model.Picture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PictureRepository extends CrudRepository<Picture, Integer> {
    List<Picture> findAll();

}
