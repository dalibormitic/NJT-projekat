package com.mitic.dalibor.dto;

import java.math.BigDecimal;

public class ProductDto {
    private Long productId;
    private String product_name;
    private BigDecimal product_price;
    private String measurement_unit;
    private String created_by;

    public ProductDto() {
    }

    public ProductDto(Long productId, String product_name, BigDecimal product_price, String measurement_unit, String created_by) {
        this.productId = productId;
        this.product_name = product_name;
        this.product_price = product_price;
        this.measurement_unit = measurement_unit;
        this.created_by = created_by;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }

    public String getMeasurement_unit() {
        return measurement_unit;
    }

    public void setMeasurement_unit(String measurement_unit) {
        this.measurement_unit = measurement_unit;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
