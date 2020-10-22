package com.mitic.dalibor.dto;

import com.mitic.dalibor.model.ReceiptItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReceiptDto {
    private Long receiptId;
    private Date receipt_date;
    private BigDecimal amount;
    private String supplied_by;
    private String signed_by;
    private List<ReceiptItemDto> receiptItems;

    public ReceiptDto() {
        receiptItems = new ArrayList<>();
    }

    public ReceiptDto(Long receiptId, Date receipt_date, BigDecimal amount, String supplied_by, String signed_by) {
        this.receiptId = receiptId;
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

    public String getSupplied_by() {
        return supplied_by;
    }

    public void setSupplied_by(String supplied_by) {
        this.supplied_by = supplied_by;
    }

    public String getSigned_by() {
        return signed_by;
    }

    public void setSigned_by(String signed_by) {
        this.signed_by = signed_by;
    }

    public List<ReceiptItemDto> getReceiptItems() {
        return receiptItems;
    }

    public void setReceiptItems(List<ReceiptItemDto> receiptItems) {
        this.receiptItems = receiptItems;
    }

    @Override
    public String toString() {
        return "ReceiptDto{" +
                "receiptId=" + receiptId +
                ", receipt_date=" + receipt_date +
                ", amount=" + amount +
                ", supplied_by='" + supplied_by + '\'' +
                ", signed_by='" + signed_by + '\'' +
                ", receiptItems=" + receiptItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptDto that = (ReceiptDto) o;
        return receiptId.equals(that.receiptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptId);
    }
}
