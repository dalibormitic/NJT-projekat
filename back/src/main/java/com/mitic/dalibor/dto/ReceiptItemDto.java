package com.mitic.dalibor.dto;

import java.math.BigDecimal;

public class ReceiptItemDto {
    private Long item_number;
    private Long receipt_id;
    private int quantity;
    private BigDecimal price;
    private String article_name;

    public ReceiptItemDto() {
    }

    public ReceiptItemDto(Long item_number, Long receipt_id, int quantity, BigDecimal price, String article_name) {
        this.item_number = item_number;
        this.receipt_id = receipt_id;
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

    public Long getReceipt_id() {
        return receipt_id;
    }

    public void setReceipt_id(Long receipt_id) {
        this.receipt_id = receipt_id;
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
}
