package com.ph.Pharmacy.dto.request;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

public class ProductRequestDto {

    private String name;
    private String category;
    private String subcategory;
    private Double price;
    private Integer stock;
    private String status;
    private String description;
    private MultipartFile mainImage;
    private List<MultipartFile> subImages;
    private Map<String, String> dynamicFields;

    // Getters and Setters
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

    public MultipartFile getMainImage() {
        return mainImage;
    }

    public void setMainImage(MultipartFile mainImage) {
        this.mainImage = mainImage;
    }

    public List<MultipartFile> getSubImages() {
        return subImages;
    }

    public void setSubImages(List<MultipartFile> subImages) {
        this.subImages = subImages;
    }

    public Map<String, String> getDynamicFields() {
        return dynamicFields;
    }

    public void setDynamicFields(Map<String, String> dynamicFields) {
        this.dynamicFields = dynamicFields;
    }
}