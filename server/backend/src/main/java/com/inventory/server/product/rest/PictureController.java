package com.inventory.server.product.rest;

import com.inventory.server.product.model.Picture;
import com.inventory.server.product.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController

@RequestMapping("/api")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping(path = "/pictures", method = RequestMethod.GET)
    public List<Picture> getAll() {
        return pictureService.getAllPictures();
    }

    @RequestMapping(path = "/pictures/upload", method = RequestMethod.POST)
    @ResponseBody
    public Picture uploadPicture(@RequestParam("picture") MultipartFile pictures, Integer id) throws Exception {
        return pictureService.createPicture(pictures, id);
    }

    @RequestMapping(value = "/pictures/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getPictureById(@PathVariable("id") Integer id) throws Exception {
        byte[] pictures = pictureService.getPictureData(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(pictures);
    }

    @RequestMapping(path = "/pictures/delete", method = RequestMethod.PUT)
    @ResponseBody
    public HttpStatus deletePicture(Integer id) throws Exception {
        pictureService.deletePicture(id);
        return HttpStatus.OK;
    }
}
