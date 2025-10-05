package com.gn.pharmacy.service.serviceImpl;
import com.gn.pharmacy.dto.request.ProductRequestDto;
import com.gn.pharmacy.dto.response.BulkUploadResponse;
import com.gn.pharmacy.dto.response.ProductResponseDto;
import com.gn.pharmacy.entity.ProductEntity;
import com.gn.pharmacy.repository.ProductRepository;
import com.gn.pharmacy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) throws Exception {
        log.debug("Creating new product with name: {}", requestDto.getProductName());

        ProductEntity entity = new ProductEntity();

        entity.setProductName(requestDto.getProductName());
        entity.setProductCategory(requestDto.getProductCategory());
        entity.setProductSubCategory(requestDto.getProductSubCategory());
        entity.setProductPrice(requestDto.getProductPrice());
        entity.setProductOldPrice(requestDto.getProductOldPrice());
        entity.setProductStock(requestDto.getProductStock());
        entity.setProductStatus(requestDto.getProductStatus());
        entity.setProductDescription(requestDto.getProductDescription());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setProductQuantity(requestDto.getProductQuantity());
        entity.setProductMainImage(requestDto.getProductMainImage().getBytes());

        List<byte[]> subImages = requestDto.getProductSubImages() != null
                ? requestDto.getProductSubImages().stream().map(file -> {
            try {
                return file.getBytes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())
                : new ArrayList<>();
        entity.setProductSubImages(subImages);
        entity.setProductDynamicFields(requestDto.getProductDynamicFields());

        // ADDED: Set product sizes
        if (requestDto.getProductSizes() != null) {
            entity.setProductSizes(requestDto.getProductSizes());
        }

        ProductEntity savedEntity = productRepository.save(entity);
        log.debug("Product saved with ID: {}", savedEntity.getProductId());
        return mapToResponseDto(savedEntity);
    }

    @Override
    public ProductResponseDto getProduct(Long id) {
        log.debug("Fetching product by ID: {}", id);
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found");
                });
        return mapToResponseDto(entity);
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(int page, int size) {
        log.debug("Fetching all products - page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        log.debug("Found {} products on page {}", productPage.getNumberOfElements(), page);
        return productPage.map(this::mapToResponseDto);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(String category) {
        log.debug("Fetching products by category: {}", category);
        List<ProductEntity> products = productRepository.findByProductCategory(category);
        log.debug("Found {} products for category: {}", products.size(), category);
        return products.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> getProductsBySubCategory(String subCategory) {
        log.debug("Fetching products by sub category: {}", subCategory);
        List<ProductEntity> products = productRepository.findByProductSubCategory(subCategory);
        log.debug("Found {} products for sub category: {}", products.size(), subCategory);
        return products.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) throws Exception {
        log.debug("Updating product with ID: {}", id);

        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found");
                });
        entity.setProductName(requestDto.getProductName());
        entity.setProductCategory(requestDto.getProductCategory());
        entity.setProductSubCategory(requestDto.getProductSubCategory());
        entity.setProductPrice(requestDto.getProductPrice());
        entity.setProductOldPrice(requestDto.getProductOldPrice());
        entity.setProductStock(requestDto.getProductStock());
        entity.setProductStatus(requestDto.getProductStatus());
        entity.setProductDescription(requestDto.getProductDescription());
        entity.setProductQuantity(requestDto.getProductQuantity());
        entity.setProductMainImage(requestDto.getProductMainImage().getBytes());

        List<byte[]> subImages = requestDto.getProductSubImages() != null
                ? requestDto.getProductSubImages().stream().map(file -> {
            try {
                return file.getBytes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())
                : new ArrayList<>();
        entity.setProductSubImages(subImages);
        entity.setProductDynamicFields(requestDto.getProductDynamicFields());

        // ADDED: Set product sizes
        if (requestDto.getProductSizes() != null) {
            entity.setProductSizes(requestDto.getProductSizes());
        }

        ProductEntity updatedEntity = productRepository.save(entity);
        log.debug("Product updated successfully with ID: {}", id);
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public ProductResponseDto patchProduct(Long id, ProductRequestDto requestDto) throws Exception {
        log.debug("Patching product with ID: {}", id);

        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new RuntimeException("Product not found");
                });

        if (requestDto.getProductName() != null) entity.setProductName(requestDto.getProductName());
        if (requestDto.getProductCategory() != null) entity.setProductCategory(requestDto.getProductCategory());
        if (requestDto.getProductSubCategory() != null) entity.setProductSubCategory(requestDto.getProductSubCategory());
        if (requestDto.getProductPrice() != null) entity.setProductPrice(requestDto.getProductPrice());
        if (requestDto.getProductOldPrice() != null) entity.setProductOldPrice(requestDto.getProductOldPrice());
        if (requestDto.getProductStock() != null) entity.setProductStock(requestDto.getProductStock());
        if (requestDto.getProductStatus() != null) entity.setProductStatus(requestDto.getProductStatus());
        if (requestDto.getProductQuantity() != null) entity.setProductQuantity(requestDto.getProductQuantity());
        if (requestDto.getProductDescription() != null) entity.setProductDescription(requestDto.getProductDescription());
        if (requestDto.getProductMainImage() != null) entity.setProductMainImage(requestDto.getProductMainImage().getBytes());
        if (requestDto.getProductSubImages() != null) {
            List<byte[]> subImages = requestDto.getProductSubImages().stream().map(file -> {
                try {
                    return file.getBytes();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            entity.setProductSubImages(subImages);
        }
        if (requestDto.getProductDynamicFields() != null) entity.setProductDynamicFields(requestDto.getProductDynamicFields());

        // ADDED: Set product sizes if provided
        if (requestDto.getProductSizes() != null) {
            entity.setProductSizes(requestDto.getProductSizes());
        }

        ProductEntity updatedEntity = productRepository.save(entity);
        log.debug("Product patched successfully with ID: {}", id);
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        log.debug("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
        log.debug("Product deleted successfully with ID: {}", id);
    }

    private ProductResponseDto mapToResponseDto(ProductEntity entity) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setProductId(entity.getProductId());
        responseDto.setProductName(entity.getProductName());
        responseDto.setProductCategory(entity.getProductCategory());
        responseDto.setProductSubCategory(entity.getProductSubCategory());
        responseDto.setProductPrice(entity.getProductPrice());
        responseDto.setProductOldPrice(entity.getProductOldPrice());
        responseDto.setProductStock(entity.getProductStock());
        responseDto.setProductStatus(entity.getProductStatus());
        responseDto.setProductDescription(entity.getProductDescription());
        responseDto.setCreatedAt(entity.getCreatedAt());
        responseDto.setProductQuantity(entity.getProductQuantity());
        responseDto.setProductMainImage("/api/products/" + entity.getProductId() + "/image");
        responseDto.setProductSubImages(IntStream.range(0, entity.getProductSubImages().size())
                .mapToObj(i -> "/api/products/" + entity.getProductId() + "/subimage/" + i)
                .collect(Collectors.toList()));
        responseDto.setProductDynamicFields(entity.getProductDynamicFields());

        // ADDED: Set product sizes
        responseDto.setProductSizes(entity.getProductSizes());

        return responseDto;
    }

    //=================== bulk product handling api ======================//
    @Override
    public BulkUploadResponse bulkCreateProducts(MultipartFile excelFile, List<MultipartFile> images) throws Exception {
        log.debug("Starting bulk product creation from Excel");

        Map<String, MultipartFile> imageMap = new HashMap<>();
        if (images != null) {
            for (MultipartFile image : images) {
                String filename = image.getOriginalFilename();
                if (filename != null) {
                    imageMap.put(filename, image);
                }
            }
        }

        int uploadedCount = 0;
        int skippedCount = 0;
        List<String> skippedReasons = new ArrayList<>();

        try (InputStream is = excelFile.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String productName = getCellValue(row.getCell(0));

                // Check for duplicate by name (add null/empty check)
                if (productName == null || productName.trim().isEmpty()) {
                    skippedCount++;
                    skippedReasons.add("Empty or missing product name in row " + (row.getRowNum() + 1));
                    continue;
                }

                if (productRepository.existsByProductName(productName)) {
                    skippedCount++;
                    skippedReasons.add("Duplicate product name: " + productName);
                    continue;
                }

                ProductRequestDto dto = new ProductRequestDto();
                dto.setProductName(productName);
                dto.setProductCategory(getCellValue(row.getCell(1)));
                dto.setProductSubCategory(getCellValue(row.getCell(2)));
                dto.setProductPrice(new BigDecimal(getCellValue(row.getCell(3))));  // Handles "120.0" fine

                // UPDATED: Safe integer parsing for old price (optional, default to null if invalid)
                String oldPriceStr = getCellValue(row.getCell(4));
                if (!oldPriceStr.trim().isEmpty()) {
                    try {
                        dto.setProductOldPrice(new BigDecimal(oldPriceStr));
                    } catch (NumberFormatException e) {
                        log.warn("Invalid old price for product {}: {}", productName, oldPriceStr);
                        // Skip or set null; here we continue without skipping product
                    }
                }

                // UPDATED: Use getIntegerCellValue for stock
                Integer stock = getIntegerCellValue(row.getCell(5));
                if (stock == null) {
                    skippedCount++;
                    skippedReasons.add("Invalid or missing stock for product: " + productName);
                    continue;
                }
                dto.setProductStock(stock);

                dto.setProductStatus(getCellValue(row.getCell(6)));
                dto.setProductDescription(getCellValue(row.getCell(7)));

                // UPDATED: Use getIntegerCellValue for quantity
                Integer quantity = getIntegerCellValue(row.getCell(8));
                if (quantity == null) {
                    skippedCount++;
                    skippedReasons.add("Invalid or missing quantity for product: " + productName);
                    continue;
                }
                dto.setProductQuantity(quantity);

                // Main image (unchanged)
                String mainImageFilename = getCellValue(row.getCell(9));
                if (mainImageFilename != null && !mainImageFilename.trim().isEmpty()) {  // Added trim
                    MultipartFile mainImage = imageMap.get(mainImageFilename.trim());
                    if (mainImage != null) {
                        dto.setProductMainImage(mainImage);
                    } else {
                        skippedCount++;
                        skippedReasons.add("Missing main image for product: " + productName + " (" + mainImageFilename + ")");
                        continue;
                    }
                } else {
                    log.warn("No main image specified for product: {}", productName);  // Optional: Allow upload without image
                    // If main image is required, add: continue; here to skip
                }

                // Sub images (comma-separated filenames) - minor fix: handle empty after trim
                String subImagesStr = getCellValue(row.getCell(10));
                List<MultipartFile> subImageFiles = new ArrayList<>();
                if (subImagesStr != null && !subImagesStr.trim().isEmpty()) {
                    String[] subFilenames = subImagesStr.split(",");
                    boolean hasMissingSub = false;
                    for (String subFilename : subFilenames) {
                        subFilename = subFilename.trim();
                        if (subFilename.isEmpty()) continue;  // Skip empty
                        MultipartFile subImage = imageMap.get(subFilename);
                        if (subImage != null) {
                            subImageFiles.add(subImage);
                        } else {
                            hasMissingSub = true;
                            skippedReasons.add("Missing sub image for product: " + productName + " (" + subFilename + ")");
                        }
                    }
                    if (hasMissingSub) {
                        skippedCount++;  // Skip whole product if any sub-image missing
                        continue;
                    }
                }
                dto.setProductSubImages(subImageFiles);

                // Dynamic fields (unchanged)
                String dynamicFieldsStr = getCellValue(row.getCell(11));
                Map<String, String> dynamicFields = new HashMap<>();
                if (dynamicFieldsStr != null && !dynamicFieldsStr.isEmpty()) {
                    String[] pairs = dynamicFieldsStr.split(",");
                    for (String pair : pairs) {
                        String[] kv = pair.split(":");
                        if (kv.length == 2) {
                            dynamicFields.put(kv[0].trim(), kv[1].trim());
                        }
                    }
                }
                dto.setProductDynamicFields(dynamicFields);

                // Sizes (comma-separated) - minor fix: handle empty
                String sizesStr = getCellValue(row.getCell(12));
                List<String> sizes = new ArrayList<>();
                if (sizesStr != null && !sizesStr.trim().isEmpty()) {
                    sizes = Arrays.stream(sizesStr.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())  // Skip empty sizes
                            .collect(Collectors.toList());
                }
                dto.setProductSizes(sizes);

                // Create the product using existing createProduct method
                try {
                    createProduct(dto);
                    uploadedCount++;
                    log.debug("Successfully uploaded product: {}", productName);
                } catch (Exception e) {
                    skippedCount++;
                    skippedReasons.add("Error creating product: " + productName + " - " + e.getMessage());
                    log.error("Failed to create product {}: {}", productName, e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error("Error processing Excel file: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process Excel file: " + e.getMessage(), e);
        }

        BulkUploadResponse response = new BulkUploadResponse();
        response.setUploadedCount(uploadedCount);
        response.setSkippedCount(skippedCount);
        response.setSkippedReasons(skippedReasons);

        log.debug("Bulk creation completed: {} uploaded, {} skipped", uploadedCount, skippedCount);
        return response;
    }

    // NEW HELPER METHOD: Add this to ProductServiceImpl.java (handles numeric decimals safely)
    private Integer getIntegerCellValue(Cell cell) {
        if (cell == null) return null;
        try {
            switch (cell.getCellType()) {
                case STRING:
                    String strVal = cell.getStringCellValue().trim();
                    if (strVal.isEmpty()) return null;
                    return Integer.parseInt(strVal);
                case NUMERIC:
                    double numVal = cell.getNumericCellValue();
                    if (numVal == Math.floor(numVal)) {  // Check if whole number
                        return (int) numVal;
                    } else {
                        log.warn("Non-integer numeric value found: {}", numVal);
                        return null;  // Or (int) Math.floor(numVal) if you want to truncate
                    }
                case BOOLEAN:
                    return cell.getBooleanCellValue() ? 1 : 0;
                default:
                    return null;
            }
        } catch (NumberFormatException e) {
            log.warn("Failed to parse integer from cell: {}", cell);
            return null;
        }
    }

    // Keep your existing getCellValue for strings/decimals
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());  // Keep as-is for BigDecimal
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }


}