package com.mitic.dalibor.dto;

import java.math.BigDecimal;

public class BillItemDto {
    private Long item_number;
    private Long bill_id;
    private int quantity;
    private BigDecimal price;
    private String article_name;

    public BillItemDto() {
    }

    public BillItemDto(Long item_number, Long bill_id, int quantity, BigDecimal price, String article_name) {
        this.item_number = item_number;
        this.bill_id = bill_id;
        this.quantity = quantity;
        this.price = price;
        this.article_name = article_name;
    }

    public Long getItem_number() {
        return item_number;
    }

    public void setItem_number(Long item_number) {
        this.item_number = item_number;
    }

    public Long getBill_id() {
        return bill_id;
    }

    public void setBill_id(Long bill_id) {
        this.bill_id = bill_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    @Override
    public String toString() {
        return "BillItemDto{" +
                "item_number=" + item_number +
                ", bill_id=" + bill_id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", article_name='" + article_name + '\'' +
                '}';
    }
}
