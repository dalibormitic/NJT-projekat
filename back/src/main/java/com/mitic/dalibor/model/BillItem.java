package com.mitic.dalibor.model;

import com.mitic.dalibor.model.IdClasses.BillItemId;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "BillItem")
@IdClass(BillItemId.class)
@Table(name = "stavkaracuna")
public class BillItem {
    @Id
    @Column(name = "bill_id")
    private Long billId;
    @Id
    private Long item_number;
    private int quantity;
    private BigDecimal price;
    private Long article_id;

    public BillItem(Long item_number, Long bill_id, int quantity, BigDecimal price, Long article_id) {
        this.item_number = item_number;
        this.billId = bill_id;
        this.quantity = quantity;
        this.price = price;
        this.article_id = article_id;
    }

    public BillItem() {
    }

    public Long getItem_number() {
        return item_number;
    }

    public void setItem_number(Long item_number) {
        this.item_number = item_number;
    }

    public Long getBill_id() {
        return billId;
    }

    public void setBill_id(Long bill_id) {
        this.billId = bill_id;
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
        return "BillItem{" +
                "item_number=" + item_number +
                ", billId=" + billId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", article_id=" + article_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillItem billItem = (BillItem) o;
        return billId.equals(billItem.billId) &&
                item_number.equals(billItem.item_number);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
