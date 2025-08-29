package com.ph.Pharmacy.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private String subcategory;

    private Double price;

    private Integer stock;

    private String status;

    private String description;

    private LocalDateTime createdAt;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] mainImage;

    @ElementCollection
    @CollectionTable(name = "product_sub_images", joinColumns = @JoinColumn(name = "product_id"))
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private List<byte[]> subImages;

    @ElementCollection
    @CollectionTable(name = "product_dynamic_fields", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "field_key")
    @Column(name = "field_value")
    private Map<String, String> dynamicFields;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getMainImage() {
        return mainImage;
    }

    public void setMainImage(byte[] mainImage) {
        this.mainImage = mainImage;
    }

    public List<byte[]> getSubImages() {
        return subImages;
    }

    public void setSubImages(List<byte[]> subImages) {
        this.subImages = subImages;
    }

    public Map<String, String> getDynamicFields() {
        return dynamicFields;
    }

    public void setDynamicFields(Map<String, String> dynamicFields) {
        this.dynamicFields = dynamicFields;
    }
}