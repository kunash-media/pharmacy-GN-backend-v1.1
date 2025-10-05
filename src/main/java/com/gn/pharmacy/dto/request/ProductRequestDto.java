package com.gn.pharmacy.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductRequestDto {

    private String productName;
    private String productCategory;
    private String productSubCategory;
    private BigDecimal productPrice;
    private BigDecimal productOldPrice;
    private String productStock;
    private String productStatus;
    private String productDescription;
    private Integer productQuantity;
    private MultipartFile productMainImage;
    private List<MultipartFile> productSubImages;
    private Map<String, String> productDynamicFields;
    private List<String> productSizes;

    // Default constructor
    public ProductRequestDto() {}

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductOldPrice() {
        return productOldPrice;
    }

    public void setProductOldPrice(BigDecimal productOldPrice) {
        this.productOldPrice = productOldPrice;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public MultipartFile getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(MultipartFile productMainImage) {
        this.productMainImage = productMainImage;
    }

    public List<MultipartFile> getProductSubImages() {
        return productSubImages;
    }

    public void setProductSubImages(List<MultipartFile> productSubImages) {
        this.productSubImages = productSubImages;
    }

    public Map<String, String> getProductDynamicFields() {
        return productDynamicFields;
    }

    public void setProductDynamicFields(Map<String, String> productDynamicFields) {
        this.productDynamicFields = productDynamicFields;
    }

    public List<String> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(List<String> productSizes) {
        this.productSizes = productSizes;
    }
}