package com.packt.webstore.domain;

import com.packt.webstore.validator.ProductId;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class Product {
    @Pattern(regexp = "P[0-9]+", message = "{com.packt.webstore.domain.Product.ProductId}")
    @ProductId
    private String productId;
    @Size(min = 4, max = 50, message = "com.packt.webstore.domain.Product.Name")
    private String name;
    @Min(value = 0, message = "com.packt.webstore.domain.Product.PriceMin")
    @Digits(integer = 8, fraction = 2, message = "com.packt.webstore.domain.Product.PriceDigits")
    @NotNull(message = "com.packt.webstore.domain.Product.PriceNotNull")
    private BigDecimal unitPrice;
    private String description;
    private String manufacturer;
    private String category;
    private long unitsInStock;
    private long unitsInOrder;
    private boolean discontinued;
    private String condition;
    private MultipartFile productImage;
    private MultipartFile productPdf;

    public Product() {
        super();
    }

    public Product(String productId, String name, BigDecimal unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public long getUnitsInOrder() {
        return unitsInOrder;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public String getCondition() {
        return condition;
    }

    public MultipartFile getProductImage() {
        return productImage;
    }

    public MultipartFile getProductPdf() {
        return productPdf;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public void setUnitsInOrder(long unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }

    public void setProductPdf(MultipartFile productPdf) {
        this.productPdf = productPdf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } if (o == null) {
            return false;
        } if (getClass() != o.getClass()) {
            return false;
        }
        Product other = (Product) o;
        if (productId == null) {
            if (other.productId != null) {
                return false;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime & result + ((productId == null) ? 0 :productId.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
