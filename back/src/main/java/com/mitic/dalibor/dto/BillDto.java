package com.mitic.dalibor.dto;

import com.mitic.dalibor.model.BillItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BillDto {
    private Long bill_id;
    private Date bill_date;
    private BigDecimal bill_amount;
    private boolean processed;
    private String signed_by;
    private List<BillItemDto> billItems;

    public BillDto() {
        billItems = new ArrayList<>();
    }

    public BillDto(Long billId, Date bill_date, BigDecimal bill_amount, boolean processed, String signed_by) {
        this.bill_id = billId;
        this.bill_date = bill_date;
        this.bill_amount = bill_amount;
        this.processed = processed;
        this.signed_by = signed_by;
    }

    public Long getBill_id() {
        return bill_id;
    }

    public void setBill_id(Long bill_id) {
        this.bill_id = bill_id;
    }

    public Date getBill_date() {
        return bill_date;
    }

    public void setBill_date(Date bill_date) {
        this.bill_date = bill_date;
    }

    public BigDecimal getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(BigDecimal bill_amount) {
        this.bill_amount = bill_amount;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public String getSigned_by() {
        return signed_by;
    }

    public void setSigned_by(String signed_by) {
        this.signed_by = signed_by;
    }

    public List<BillItemDto> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItemDto> billItems) {
        this.billItems = billItems;
    }

    @Override
    public String toString() {
        return "BillDto{" +
                "bill_id=" + bill_id +
                ", bill_date=" + bill_date +
                ", bill_amount=" + bill_amount +
                ", processed=" + processed +
                ", signed_by='" + signed_by + '\'' +
                ", billItems=" + billItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillDto billDto = (BillDto) o;
        return bill_id.equals(billDto.bill_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bill_id);
    }
}
