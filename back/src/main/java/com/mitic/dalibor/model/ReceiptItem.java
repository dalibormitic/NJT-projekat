package com.mitic.dalibor.model;

import com.mitic.dalibor.model.IdClasses.ReceiptItemId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "ReceiptItem")
@IdClass(ReceiptItemId.class)
@Table(name = "stavkaprijemnice")
public class ReceiptItem {
    @Id
    @Column(name = "receipt_id")
    private Long receiptId;
    @Id
    private Long item_number;
    private int quantity;
    private BigDecimal price;
    private Long article_id;

    public ReceiptItem() {
    }

    public ReceiptItem(Long receiptId, Long item_number, int quantity, BigDecimal price, Long article_id) {
        this.receiptId = receiptId;
        this.item_number = item_number;
        this.quantity = quantity;
        this.price = price;
        this.article_id = article_id;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Long getItem_number() {
        return item_number;
    }

    public void setItem_number(Long item_number) {
        this.item_number = item_number;
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

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    @Override
    public String toString() {
        return "ReceiptItem{" +
                "receiptId=" + receiptId +
                ", item_number=" + item_number +
                ", quantity=" + quantity +
                ", price=" + price +
                ", article_id=" + article_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem that = (ReceiptItem) o;
        return receiptId.equals(that.receiptId) &&
                item_number.equals(that.item_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptId, item_number);
    }
}
