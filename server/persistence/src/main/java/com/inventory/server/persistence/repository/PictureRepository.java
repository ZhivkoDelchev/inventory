package com.inventory.server.persistence.repository;

import com.inventory.server.persistence.model.Picture;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PictureRepository extends CrudRepository<Picture, Integer> {
    List<Picture> findAll();

}
