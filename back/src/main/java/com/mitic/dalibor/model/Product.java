package com.mitic.dalibor.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Product")
@Table(name = "roba")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    private BigDecimal product_price;
    private String measurement_unit;
    private Long created_by;

    public Product() {
    }

    public Product(String product_name, BigDecimal product_price, String measurement_unit, Long created_by) {
        this.productName = product_name;
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
        return productName;
    }

    public void setProduct_name(String product_name) {
        this.productName = product_name;
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

    public Long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", product_price=" + product_price +
                ", measurement_unit='" + measurement_unit + '\'' +
                ", created_by=" + created_by +
                '}';
    }
}
