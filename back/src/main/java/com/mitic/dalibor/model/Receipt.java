package com.mitic.dalibor.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Receipt")
@Table(name = "prijemnica")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long receiptId;
    private Date receipt_date;
    private BigDecimal amount;
    private Long supplied_by;
    private Long signed_by;

    public Receipt() {
    }

    public Receipt(Date receipt_date, BigDecimal amount, Long supplied_by, Long signed_by) {
        this.receipt_date = receipt_date;
        this.amount = amount;
        this.supplied_by = supplied_by;
        this.signed_by = signed_by;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public Date getReceipt_date() {
        return receipt_date;
    }

    public void setReceipt_date(Date receipt_date) {
        this.receipt_date = receipt_date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getSupplied_by() {
        return supplied_by;
    }

    public void setSupplied_by(Long supplied_by) {
        this.supplied_by = supplied_by;
    }

    public Long getSigned_by() {
        return signed_by;
    }

    public void setSigned_by(Long signed_by) {
        this.signed_by = signed_by;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return receiptId.equals(receipt.receiptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptId);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptId=" + receiptId +
                ", receipt_date=" + receipt_date +
                ", amount=" + amount +
                ", supplied_by=" + supplied_by +
                ", signed_by=" + signed_by +
                '}';
    }
}
