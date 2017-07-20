package com.inventory.server.product.model;

import com.inventory.server.product.service.ProductService;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "data")
    private byte[] pictureData;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    public Picture(byte[] pictureData, Product product) {
        this.pictureData = pictureData;
        this.product = product;
    }

    public Picture() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getPictureData() {
        return this.pictureData;
    }

    public void setPictureData(byte[] pictureData) {
        this.pictureData = pictureData;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
