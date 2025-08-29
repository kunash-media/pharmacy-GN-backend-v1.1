package com.ph.Pharmacy.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProductResponseDto {

    private Long id;
    private String name;
    private String category;
    private String subcategory;
    private Double price;
    private Integer stock;
    private String status;
    private String description;
    private LocalDateTime createdAt;
    private String mainImage; // URL path, e.g., "/api/products/{id}/image"
    private List<String> subImages; // URL paths, e.g., ["/api/products/{id}/subimage/0", "/api/products/{id}/subimage/1"]
    private Map<String, String> dynamicFields;

    // Getters and setters
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

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getSubImages() {
        return subImages;
    }

    public void setSubImages(List<String> subImages) {
        this.subImages = subImages;
    }

    public Map<String, String> getDynamicFields() {
        return dynamicFields;
    }

    public void setDynamicFields(Map<String, String> dynamicFields) {
        this.dynamicFields = dynamicFields;
    }
}