package com.mitic.dalibor.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Bill")
@Table(name = "racun")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;
    private Date bill_date;
    private BigDecimal bill_amount;
    private boolean processed;
    private Long signed_by;

    public Bill(Date bill_date, BigDecimal bill_amount, boolean processed, Long signed_by) {
        this.bill_date = bill_date;
        this.bill_amount = bill_amount;
        this.processed = processed;
        this.signed_by = signed_by;
    }

    public Bill() {

    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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
        Bill bill = (Bill) o;
        return billId.equals(bill.billId);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", bill_date=" + bill_date +
                ", bill_amount=" + bill_amount +
                ", processed=" + processed +
                ", signed_by=" + signed_by +
                '}';
    }
}
