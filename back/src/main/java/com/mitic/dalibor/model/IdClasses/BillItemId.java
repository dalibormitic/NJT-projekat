package com.mitic.dalibor.model.IdClasses;


import java.io.Serializable;
import java.util.Objects;

public class BillItemId implements Serializable {
    private Long billId;
    private Long item_number;

    public BillItemId() {
    }

    public BillItemId(Long billId, Long item_number) {
        this.billId = billId;
        this.item_number = item_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillItemId that = (BillItemId) o;
        return billId.equals(that.billId) &&
                item_number.equals(that.item_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, item_number);
    }
}
