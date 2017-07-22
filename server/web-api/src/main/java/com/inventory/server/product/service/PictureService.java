package com.inventory.server.product.service;

import com.inventory.server.persistence.model.Picture;
import com.inventory.server.persistence.repository.PictureRepository;
import com.inventory.server.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PictureService {

    @Autowired
    private PictureRepository picturesRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Picture> getAllPictures() {
        return picturesRepository.findAll();
    }

    public Picture getPictureById(Integer id) throws Exception {
        if (!this.picturesRepository.exists(id)) {
            throw new Exception("Picture with this ID not exist.");
        }

        return this.picturesRepository.findOne(id);
    }

    public Picture createPicture(MultipartFile pictures, Integer id) throws IOException {
        byte[] picturesData = pictures.getBytes();
        Picture picture = new Picture(picturesData, productRepository.findOne(id));
        picturesRepository.save(picture);

        return picture;
    }

    public byte[] getPictureData(Integer id) {
        return picturesRepository.findOne(id).getPictureData();
    }

    public void deletePicture(Integer id){
        picturesRepository.delete(id);
    }
}
